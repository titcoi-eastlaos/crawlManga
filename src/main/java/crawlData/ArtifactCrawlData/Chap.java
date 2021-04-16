package crawlData.ArtifactCrawlData;


public class Chap {
	private String url;
	private String chap_number;
	
	//Constructor
	public Chap(String url) {
		this.url = url;
		chap_number = chapNumber(url);
	}
	
	//Lay url
	public String getUrl() {
		return this.url;
	}
	
	//Lay chapnumber
	public String getChap_number() {
		return chap_number;
	}
	
	//Lay so chapter cua truyen
	private String chapNumber(String url) {
		int idx1 = 0;
		int idx2 = 0;
		//Lap tu dau xuong
		for(int i = 0; i < url.length(); i++) {
			if(Character.isDigit(url.charAt(i))){
				idx1 = i;
				break;
			}
		}
		//Lap tu cuoi len
		for(int i = url.length()-1; i >= 0;i--) {
			if(Character.isDigit(url.charAt(i))){
				idx2 = i;
				break;
			}
		}
		//Xoa k phan tu dau chuoi url.substring(idx1,idx2+1) => so chapter
		//Thay doi gia tri k la xong
		int k = 0; 
		int index = 0;
		String s = url.substring(idx1,idx2+1);
		char[] splitUrl = s.toCharArray();
		char[] numChap = new char[url.substring(idx1,idx2+1).length() - k];
		for (int i = k; i < url.substring(idx1,idx2+1).length();i++) {
			numChap[index] = splitUrl[i];
			index++;
		}
		return String.valueOf(numChap);
	}
	//
}
