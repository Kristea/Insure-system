package Model;


public class Feedback {
    
    private String feedback_id; //in - initital feedback / fin - final feedback
    private Insured insured;
    private Insurer insurer;
    private int rating;
    private String comments;
    
    public Feedback(){}

    public Feedback(String feedback_id, Insured insured, Insurer insurer, int rating, String comments) {
        this.feedback_id = feedback_id;
        this.insured = insured;
        this.insurer = insurer;
        this.rating = rating;
        this.comments = comments;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
}
