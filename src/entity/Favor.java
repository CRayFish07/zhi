package entity;

public class Favor {

	private String userID;
	private int articleId;
	private String articleTitle;
	private int moduleID;
	private String moduleName;
	private long time;

	public Favor(String userID, int articleId, String articleTitle,
			int moduleID, String moduleName, long time) {
		super();
		this.userID = userID;
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.moduleID = moduleID;
		this.moduleName = moduleName;
		this.time = time;
	}

	public Favor() {
		super();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public int getModuleID() {
		return moduleID;
	}

	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public long getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
