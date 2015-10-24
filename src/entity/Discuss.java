package entity;

public class Discuss {

	private int id;
	private int articleID;
	private String userID;
	private String content;
	private long time;
	private int type;
	private int itemID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public Discuss(int articleID, String userID, String content, long time,
			int itemID) {
		super();
		this.articleID = articleID;
		this.userID = userID;
		this.content = content;
		this.time = time;
		this.itemID = itemID;
	}

}
