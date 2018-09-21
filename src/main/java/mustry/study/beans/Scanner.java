package mustry.study.beans;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import mustry.study.classFile.ClassReader;

/*
 * 未完成
 * 本想着能从class文件中直接筛选出拥有特定注解的类进行返回
 * 扫描到真正的注解时遇见了特别麻烦的规则，注解中的内容需要进行详细的区分而没有特定的length进行跳过
 * 所以放弃了
 * 不过能从class文件中获取到类的全限定名，可从ClassLoader中直接加载再进行判断
 */
public class Scanner {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner();
		long start = System.currentTimeMillis();
		Class<?>[] classes = scanner.getClasses("mustry");
		System.out.println("耗时 : " + (System.currentTimeMillis() - start));
		System.out.println("共扫描出类数量 : " + classes.length);
	}

	public Class<?>[] getClasses(String packagePath) throws Exception {
		String[] classNames = getClassNames(packagePath);
		Class<?>[] classes = new Class<?>[classNames.length];
		for (int i = 0; i < classNames.length; i++)
			classes[i] = ClassLoader.getSystemClassLoader().loadClass(classNames[i].replaceAll("/", "."));
		return classes;
	}

	public String[] getClassNames(String packagePath) throws Exception {
		Enumeration<URL> urls = loadMatchingResources(packagePath.replace(".", File.separator));
		String[] packages = retrieveMatching(urls, "*.class");
		return packages;
	}

	String[] retrieveMatching(Enumeration<URL> urls, String pattern) {
		Assert.isNotNull(urls, "Urls is null");
		Set<String> packageSet = new LinkedHashSet<String>(16);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			Assert.isNotNull(url, "Url is null");
			if (isJarURL(url)) {
				packageSet.addAll(retrieveMatchingJarFiles(url, pattern));
			} else {
				packageSet.addAll(retrieveMatchingFiles(getFile(url), pattern));
			}
		}
		return packageSet.toArray(new String[packageSet.size()]);
	}

	Collection<String> retrieveMatchingFiles(File file, String pattern) {
		Assert.isNotNull(file, "File is null");
		if (!file.exists() || !file.isDirectory() || !file.canRead()) {
			return Collections.emptySet();
		}
		Set<File> files = new LinkedHashSet<File>(16);
		doRetrieveMatchingFiles(file, pattern, files);
		return resolveFile2PackageUri(files, new LinkedHashSet<String>(files.size()));
	}

	void doRetrieveMatchingFiles(File file, String pattern, Collection<File> files) {
		File[] content = file.listFiles();
		if (content == null)
			return;
		for (File item : content) {
			String currPath = item.getAbsolutePath().replace(File.separator, "/");
			if (item.isDirectory() && matchStart(currPath + "/", pattern))
				if (item.canRead())
					doRetrieveMatchingFiles(item, pattern, files);
			if (match(currPath, pattern))
				files.add(item);
		}
	}

	Collection<String> retrieveMatchingJarFiles(URL url, String pattern) {
		JarFile jarFile = null;
		boolean needCloseJarFile = false;
		try {
			URLConnection con = url.openConnection();
			if (!(con instanceof JarURLConnection))
				return Collections.emptySet(); // 暂时不解决非 JarURLConnection 的问题
			JarURLConnection jarCon = (JarURLConnection) con;
			needCloseJarFile = !jarCon.getUseCaches();

			JarEntry jarEntry = jarCon.getJarEntry();
			String entryName = jarEntry != null ? jarEntry.getName() : "";
			return doRetrieveMatchingJarFiles(jarCon.getJarFile(), entryName, pattern);
		} catch (IOException e) {
			throw new RuntimeException("retrieve JarFiles error at \"" + url.getFile() + "\".");
		} finally {
			if (jarFile != null && needCloseJarFile)
				try {
					jarFile.close();
				} catch (IOException e) {
				}
		}
	}

	Collection<String> doRetrieveMatchingJarFiles(JarFile jarFile, String rootEntryName, String pattern) {
		Assert.isNotNull(jarFile, "jarFile is null");
		Set<String> packages = new LinkedHashSet<String>(8);
		for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
			JarEntry entry = entries.nextElement();
			String entryPath = entry.getName();
			if (entryPath.startsWith(rootEntryName))
				if (match(entryPath, pattern))
					packages.add(processPackageUri(entryPath));
		}
		return packages;
	}

	File getFile(URL url) {
		try {
			return new File(url.toURI());
		} catch (URISyntaxException e) {
			return new File(url.getFile().replaceAll("%20", " "));
		}
	}

	Enumeration<URL> loadMatchingResources(String packagePath) {
		try {
			return ClassLoader.getSystemResources(packagePath.replace(File.separator, "/"));
		} catch (IOException e) {
			throw new RuntimeException("packagePath : \"" + packagePath + "\" load error");
		}
	}

	String resolveFile2PackageUri(File file) {
		Assert.isNotNull(file, "file is null");
		return new ClassReader(file).getClassName();
	}

	Collection<String> resolveFile2PackageUri(Collection<File> files, Collection<String> packages) {
		Assert.isNotNull(files, "Files is null");
		if (packages == null)
			packages = new ArrayList<String>(files.size());

		for (File file : files)
			packages.add(resolveFile2PackageUri(file));
		return packages;
	}

	String processPackageUri(String packageUri) {
		if (packageUri.endsWith(".class"))
			return packageUri.substring(0, packageUri.length() - ".class".length());
		return packageUri;
	}

	/**
	 * 判断文件路径和指定模式是否匹配
	 * 
	 * @param path
	 * @param pattern
	 * @return
	 */
	protected boolean match(String path, String pattern) {
		return path.lastIndexOf(".class") != -1;
	}

	/**
	 * 判断文件夹路径和指定模式是否匹配
	 * 
	 * @param path
	 * @param pattern
	 * @return
	 */
	protected boolean matchStart(String path, String pattern) {
		return true;
	}

	final String URL_PROTOCOL_JAR = "jar";
	final String URL_PROTOCOL_WAR = "war";
	String URL_PROTOCOL_ZIP = "zip";

	boolean isJarURL(URL url) {
		String protocol = url.getProtocol();
		return (URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_WAR.equals(protocol)
				|| URL_PROTOCOL_ZIP.equals(protocol));
	}

	static class Assert {
		public final static void isNull(Object obj, String message) {
			if (obj != null)
				throw new IllegalArgumentException(message);
		}

		public final static void isNotNull(Object obj, String message) {
			if (obj == null)
				throw new IllegalArgumentException(message);
		}
	}
}