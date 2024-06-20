package Model;

import java.util.Date;

public class Exam {
  private String name;
  private String description;
  private Date openTime;
  private Date closeTime;
  private String subject;
  private int duration;
  private boolean canRepeat;
  private boolean canReviewed;
  private float total;
  private int easies;
  private int mediums;
  private int hards;
  private int teacherId;

  public Exam() {
  }

  public Exam(String name, String description, Date openTime, Date closeTime, String subject, int duration,
      boolean canRepeat, boolean canReviewed, float total, int easies, int mediums, int hards, int teacherId) {
    this.name = name;
    this.description = description;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.subject = subject;
    this.duration = duration;
    this.canRepeat = canRepeat;
    this.canReviewed = canReviewed;
    this.total = total;
    this.easies = easies;
    this.mediums = mediums;
    this.hards = hards;
    this.teacherId = teacherId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Date getOpenTime() {
    return openTime;
  }

  public Date getCloseTime() {
    return closeTime;
  }

  public String getSubject() {
    return subject;
  }

  public int getDuration() {
    return duration;
  }

  public boolean getCanRepeat() {
    return canRepeat;
  }

  public boolean getCanReviewed() {
    return canReviewed;
  }

  public float getTotal() {
    return total;
  }

  public int getEasies() {
    return easies;
  }

  public int getMediums() {
    return mediums;
  }

  public int getHards() {
    return hards;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setOpenTime(Date openTime) {
    this.openTime = openTime;
  }

  public void setCloseTime(Date closeTime) {
    this.closeTime = closeTime;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setCanRepeat(boolean canRepeat) {
    this.canRepeat = canRepeat;
  }

  public void setCanReviewed(boolean canReviewed) {
    this.canReviewed = canReviewed;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public void setEasies(int easies) {
    this.easies = easies;
  }

  public void setMediums(int mediums) {
    this.mediums = mediums;
  }

  public void setHards(int hards) {
    this.hards = hards;
  }
}
