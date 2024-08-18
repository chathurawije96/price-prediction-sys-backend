package edu.icbt.pps.pricepredictionsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "users_feedback")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@ToString(callSuper = true, includeFieldNames = false)
public class UserFeedback extends EntityBase {

  public UserFeedback(Long id) {
    super.setId(id);
  }

  @NotBlank(message = "Message cannot be empty")
  private String message;
  private Integer rate;
  @Length(max = 10,min = 10, message = "Please enter valid mobile number !")
  private String contactNumber;
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  public UserFeedback(Long id, Status status, Date createdAt, Date updatedAt, String message, Integer rate, String contactNumber, User user) {
    super(id, status, createdAt, updatedAt);
    this.message = message;
    this.rate = rate;
    this.contactNumber = contactNumber;
    this.user = user;
  }
}
