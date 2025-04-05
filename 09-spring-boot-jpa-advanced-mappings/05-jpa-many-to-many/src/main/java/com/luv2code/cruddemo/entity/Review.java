package com.luv2code.cruddemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
  // Define fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "comment")
  private String comment;

  // Define constructors
  public Review() {
  }

  public Review(String comment) {
    this.comment = comment;
  }

  // Define getter/ setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  // Define toString
  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", comment='" + comment + '\'' +
        '}';
  }
}
