package mustry.study.mains;

public class Main2 {
	public static void main(String[] args) {
		String data = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<data.length();i++) {
			String item = data.substring(i,i+1);
			sb.append("'").append(item).append("',");
		}
		sb.delete(sb.length()-1,sb.length());
		System.out.println(sb.toString());
	}
}
