package crawlData.ArtifactCrawlData;
//
import java.util.Scanner;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;


//https://truyendep.com/attack-on-titan-2r/
public class App 
{
	//Input: url page truyen co chua cac chapter
	//Output: ArrayList<Chap> cac chapter
	private static ArrayList<Chap> getAllChapInPage(String urls) throws IOException{
		ArrayList<Chap> listChap = new ArrayList<Chap>();
		Document doc = Jsoup.connect(urls).get();
		//https://jsoup.org/apidocs/org/jsoup/nodes/Element.html#getElementsByClass(java.lang.String)
		Elements elms = doc.getElementsByClass("row");
		for(int i = 0;i < elms.size();i++) {
			Elements elm_row = elms.get(i).getElementsByTag("a");
			for(int j = 0; j < elm_row.size();j++) {
				String link_chap = elm_row.first().absUrl("href");
				listChap.add(new Chap(link_chap));
			}
		}
		
		return listChap;
	}
	
	//Input: link cua chap truyen
	//Output: ArrayList<String> cac image
	private static ArrayList<String> listImgOnPage(String pageURL) throws IOException{
		Document doc = Jsoup.connect(pageURL).get();
		ArrayList<String> list_img = new ArrayList<String>();
		Elements elms = doc.getElementsByClass("vung_doc");
		Elements e = elms.get(0).getElementsByTag("img"); //elms chi co 1 phan tu 
		for(int i = 0; i < e.size();i++) {
			String url = e.get(i).absUrl("src");
			if(url.equals("")) {
				continue;
			} 
			list_img.add(url);
		}
		return list_img;
	}
	//save Image
	//Input:link anh, ten anh muon luu ve, ten thu muc muon luu vao
	//Output:1 Image
	private static void saveImg(String src_image,int name,String dir) {
		try {
			URL url = new URL(src_image);
			InputStream in = url.openStream();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "//" + Integer.toString(name)));
			for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
			System.out.println("Download complete " + Integer.toString(name) + ".jpg");
			out.close();
            in.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Can not download file!!");
		}
	}
	
	
	//Main
    public static void main( String[] args ) throws Exception
    {
    	String urls = "https://truyendep.com/attack-on-titan-2r/";
    	String dir = "E://TestCrawlData";
    	ArrayList<Chap> listChap = getAllChapInPage(urls);
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Attack on Titan-full 139 chapter");
    	System.out.println("-crawl tu truyendep.com-");
    	
    	// Test in ra link va ten tat ca chapter
    	for(int i = 0; i < listChap.size();i++) {
    		int stt = listChap.size() - i;
    		System.out.println(stt +": "+listChap.get(i).getUrl());
    		System.out.println("chap "+listChap.get(i).getChap_number());
    	}
    	
    	System.out.println("Nhap SO THU TU chapter can tai: ");
    	int chap = sc.nextInt();
    	ArrayList<String> listImg = listImgOnPage(listChap.get(chap-1).getUrl());
    	//Download caca trang truyen ve duong dan dir 
    	/*
    	for(int i = 0; i < listImg.size();i++) {
    		System.out.print("Downloading image "+ i + ".....");
    		saveImg(listImg.get(i),i,dir);
    	}
    	*/
  	
    	//In ra full link trang img truyen
    	for(int i = 0; i < listImg.size();i++) {
    		System.out.println(listImg.get(i));
    	}
    	sc.close();
    }
}
