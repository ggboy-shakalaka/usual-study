package mustry.study.common.utils.string;

public class StringUtil {

	public static String toUpperCase(String str) {
		return changeFirstCharacterCase(str, true);
	}

	public static String toLowerCase(String str) {
		return changeFirstCharacterCase(str, false);
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0)
			return str;
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize)
			sb.append(Character.toUpperCase(str.charAt(0)));
		else
			sb.append(Character.toLowerCase(str.charAt(0)));
		return sb.append(str.substring(1)).toString();
	}
}
