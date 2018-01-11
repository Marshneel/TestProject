package APITesting.com.org.classes.advanced;

public class PostsAd {
	
	private String id;
	private String author;
	private String title;
	private InfoAd[] info;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public InfoAd[] getInfo(){
		return info;
	}
	
	public void setInfo(InfoAd[] info){
		this.info=info;
	}
	
}
