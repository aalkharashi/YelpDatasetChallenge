package BusinessTips;

/*
 * This code created CSV after extracting information from yelp dataset files.
 * @author:Vimalendu Shekhar
 */
public class ReviewData {

	public String business_id;
	public String userid;
	public String reviewText;
	public String tipsText;
	public String stars;
	public String likes;
	public String funny;
	public String useful;
	public String cool;
	public int textsize;

	public String getBusiness_id() {
		return business_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getFunny() {
		return funny;
	}

	public void setFunny(String funny) {
		this.funny = funny;
	}

	public String getUseful() {
		return useful;
	}

	public void setUseful(String useful) {
		this.useful = useful;
	}

	public String getCool() {
		return cool;
	}

	public void setCool(String cool) {
		this.cool = cool;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getTipsText() {
		return tipsText;
	}

	public void setTipsText(String tipsText) {
		this.tipsText = tipsText;
	}
}
