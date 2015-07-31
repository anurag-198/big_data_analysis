// ORM class for table 'UserProfile'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Jun 29 19:43:57 IST 2015
// For connector: org.apache.sqoop.manager.DirectMySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserProfile extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Long userId;
  public Long get_userId() {
    return userId;
  }
  public void set_userId(Long userId) {
    this.userId = userId;
  }
  public UserProfile with_userId(Long userId) {
    this.userId = userId;
    return this;
  }
  private Long lmsUserId;
  public Long get_lmsUserId() {
    return lmsUserId;
  }
  public void set_lmsUserId(Long lmsUserId) {
    this.lmsUserId = lmsUserId;
  }
  public UserProfile with_lmsUserId(Long lmsUserId) {
    this.lmsUserId = lmsUserId;
    return this;
  }
  private String lmsName;
  public String get_lmsName() {
    return lmsName;
  }
  public void set_lmsName(String lmsName) {
    this.lmsName = lmsName;
  }
  public UserProfile with_lmsName(String lmsName) {
    this.lmsName = lmsName;
    return this;
  }
  private String orgName;
  public String get_orgName() {
    return orgName;
  }
  public void set_orgName(String orgName) {
    this.orgName = orgName;
  }
  public UserProfile with_orgName(String orgName) {
    this.orgName = orgName;
    return this;
  }
  private String name;
  public String get_name() {
    return name;
  }
  public void set_name(String name) {
    this.name = name;
  }
  public UserProfile with_name(String name) {
    this.name = name;
    return this;
  }
  private String gender;
  public String get_gender() {
    return gender;
  }
  public void set_gender(String gender) {
    this.gender = gender;
  }
  public UserProfile with_gender(String gender) {
    this.gender = gender;
    return this;
  }
  private java.sql.Date registrationDate;
  public java.sql.Date get_registrationDate() {
    return registrationDate;
  }
  public void set_registrationDate(java.sql.Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public UserProfile with_registrationDate(java.sql.Date registrationDate) {
    this.registrationDate = registrationDate;
    return this;
  }
  private String emailId;
  public String get_emailId() {
    return emailId;
  }
  public void set_emailId(String emailId) {
    this.emailId = emailId;
  }
  public UserProfile with_emailId(String emailId) {
    this.emailId = emailId;
    return this;
  }
  private String mothertounge;
  public String get_mothertounge() {
    return mothertounge;
  }
  public void set_mothertounge(String mothertounge) {
    this.mothertounge = mothertounge;
  }
  public UserProfile with_mothertounge(String mothertounge) {
    this.mothertounge = mothertounge;
    return this;
  }
  private String highestEduDegree;
  public String get_highestEduDegree() {
    return highestEduDegree;
  }
  public void set_highestEduDegree(String highestEduDegree) {
    this.highestEduDegree = highestEduDegree;
  }
  public UserProfile with_highestEduDegree(String highestEduDegree) {
    this.highestEduDegree = highestEduDegree;
    return this;
  }
  private String goals;
  public String get_goals() {
    return goals;
  }
  public void set_goals(String goals) {
    this.goals = goals;
  }
  public UserProfile with_goals(String goals) {
    this.goals = goals;
    return this;
  }
  private String city;
  public String get_city() {
    return city;
  }
  public void set_city(String city) {
    this.city = city;
  }
  public UserProfile with_city(String city) {
    this.city = city;
    return this;
  }
  private String state;
  public String get_state() {
    return state;
  }
  public void set_state(String state) {
    this.state = state;
  }
  public UserProfile with_state(String state) {
    this.state = state;
    return this;
  }
  private Integer active;
  public Integer get_active() {
    return active;
  }
  public void set_active(Integer active) {
    this.active = active;
  }
  public UserProfile with_active(Integer active) {
    this.active = active;
    return this;
  }
  private java.sql.Timestamp firstAccesDate;
  public java.sql.Timestamp get_firstAccesDate() {
    return firstAccesDate;
  }
  public void set_firstAccesDate(java.sql.Timestamp firstAccesDate) {
    this.firstAccesDate = firstAccesDate;
  }
  public UserProfile with_firstAccesDate(java.sql.Timestamp firstAccesDate) {
    this.firstAccesDate = firstAccesDate;
    return this;
  }
  private java.sql.Timestamp lastAccessDate;
  public java.sql.Timestamp get_lastAccessDate() {
    return lastAccessDate;
  }
  public void set_lastAccessDate(java.sql.Timestamp lastAccessDate) {
    this.lastAccessDate = lastAccessDate;
  }
  public UserProfile with_lastAccessDate(java.sql.Timestamp lastAccessDate) {
    this.lastAccessDate = lastAccessDate;
    return this;
  }
  private Integer allowCert;
  public Integer get_allowCert() {
    return allowCert;
  }
  public void set_allowCert(Integer allowCert) {
    this.allowCert = allowCert;
  }
  public UserProfile with_allowCert(Integer allowCert) {
    this.allowCert = allowCert;
    return this;
  }
  private Integer yearOfBirth;
  public Integer get_yearOfBirth() {
    return yearOfBirth;
  }
  public void set_yearOfBirth(Integer yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }
  public UserProfile with_yearOfBirth(Integer yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
    return this;
  }
  private Integer pincode;
  public Integer get_pincode() {
    return pincode;
  }
  public void set_pincode(Integer pincode) {
    this.pincode = pincode;
  }
  public UserProfile with_pincode(Integer pincode) {
    this.pincode = pincode;
    return this;
  }
  private String aadharId;
  public String get_aadharId() {
    return aadharId;
  }
  public void set_aadharId(String aadharId) {
    this.aadharId = aadharId;
  }
  public UserProfile with_aadharId(String aadharId) {
    this.aadharId = aadharId;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfile)) {
      return false;
    }
    UserProfile that = (UserProfile) o;
    boolean equal = true;
    equal = equal && (this.userId == null ? that.userId == null : this.userId.equals(that.userId));
    equal = equal && (this.lmsUserId == null ? that.lmsUserId == null : this.lmsUserId.equals(that.lmsUserId));
    equal = equal && (this.lmsName == null ? that.lmsName == null : this.lmsName.equals(that.lmsName));
    equal = equal && (this.orgName == null ? that.orgName == null : this.orgName.equals(that.orgName));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.registrationDate == null ? that.registrationDate == null : this.registrationDate.equals(that.registrationDate));
    equal = equal && (this.emailId == null ? that.emailId == null : this.emailId.equals(that.emailId));
    equal = equal && (this.mothertounge == null ? that.mothertounge == null : this.mothertounge.equals(that.mothertounge));
    equal = equal && (this.highestEduDegree == null ? that.highestEduDegree == null : this.highestEduDegree.equals(that.highestEduDegree));
    equal = equal && (this.goals == null ? that.goals == null : this.goals.equals(that.goals));
    equal = equal && (this.city == null ? that.city == null : this.city.equals(that.city));
    equal = equal && (this.state == null ? that.state == null : this.state.equals(that.state));
    equal = equal && (this.active == null ? that.active == null : this.active.equals(that.active));
    equal = equal && (this.firstAccesDate == null ? that.firstAccesDate == null : this.firstAccesDate.equals(that.firstAccesDate));
    equal = equal && (this.lastAccessDate == null ? that.lastAccessDate == null : this.lastAccessDate.equals(that.lastAccessDate));
    equal = equal && (this.allowCert == null ? that.allowCert == null : this.allowCert.equals(that.allowCert));
    equal = equal && (this.yearOfBirth == null ? that.yearOfBirth == null : this.yearOfBirth.equals(that.yearOfBirth));
    equal = equal && (this.pincode == null ? that.pincode == null : this.pincode.equals(that.pincode));
    equal = equal && (this.aadharId == null ? that.aadharId == null : this.aadharId.equals(that.aadharId));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserProfile)) {
      return false;
    }
    UserProfile that = (UserProfile) o;
    boolean equal = true;
    equal = equal && (this.userId == null ? that.userId == null : this.userId.equals(that.userId));
    equal = equal && (this.lmsUserId == null ? that.lmsUserId == null : this.lmsUserId.equals(that.lmsUserId));
    equal = equal && (this.lmsName == null ? that.lmsName == null : this.lmsName.equals(that.lmsName));
    equal = equal && (this.orgName == null ? that.orgName == null : this.orgName.equals(that.orgName));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.registrationDate == null ? that.registrationDate == null : this.registrationDate.equals(that.registrationDate));
    equal = equal && (this.emailId == null ? that.emailId == null : this.emailId.equals(that.emailId));
    equal = equal && (this.mothertounge == null ? that.mothertounge == null : this.mothertounge.equals(that.mothertounge));
    equal = equal && (this.highestEduDegree == null ? that.highestEduDegree == null : this.highestEduDegree.equals(that.highestEduDegree));
    equal = equal && (this.goals == null ? that.goals == null : this.goals.equals(that.goals));
    equal = equal && (this.city == null ? that.city == null : this.city.equals(that.city));
    equal = equal && (this.state == null ? that.state == null : this.state.equals(that.state));
    equal = equal && (this.active == null ? that.active == null : this.active.equals(that.active));
    equal = equal && (this.firstAccesDate == null ? that.firstAccesDate == null : this.firstAccesDate.equals(that.firstAccesDate));
    equal = equal && (this.lastAccessDate == null ? that.lastAccessDate == null : this.lastAccessDate.equals(that.lastAccessDate));
    equal = equal && (this.allowCert == null ? that.allowCert == null : this.allowCert.equals(that.allowCert));
    equal = equal && (this.yearOfBirth == null ? that.yearOfBirth == null : this.yearOfBirth.equals(that.yearOfBirth));
    equal = equal && (this.pincode == null ? that.pincode == null : this.pincode.equals(that.pincode));
    equal = equal && (this.aadharId == null ? that.aadharId == null : this.aadharId.equals(that.aadharId));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.userId = JdbcWritableBridge.readLong(1, __dbResults);
    this.lmsUserId = JdbcWritableBridge.readLong(2, __dbResults);
    this.lmsName = JdbcWritableBridge.readString(3, __dbResults);
    this.orgName = JdbcWritableBridge.readString(4, __dbResults);
    this.name = JdbcWritableBridge.readString(5, __dbResults);
    this.gender = JdbcWritableBridge.readString(6, __dbResults);
    this.registrationDate = JdbcWritableBridge.readDate(7, __dbResults);
    this.emailId = JdbcWritableBridge.readString(8, __dbResults);
    this.mothertounge = JdbcWritableBridge.readString(9, __dbResults);
    this.highestEduDegree = JdbcWritableBridge.readString(10, __dbResults);
    this.goals = JdbcWritableBridge.readString(11, __dbResults);
    this.city = JdbcWritableBridge.readString(12, __dbResults);
    this.state = JdbcWritableBridge.readString(13, __dbResults);
    this.active = JdbcWritableBridge.readInteger(14, __dbResults);
    this.firstAccesDate = JdbcWritableBridge.readTimestamp(15, __dbResults);
    this.lastAccessDate = JdbcWritableBridge.readTimestamp(16, __dbResults);
    this.allowCert = JdbcWritableBridge.readInteger(17, __dbResults);
    this.yearOfBirth = JdbcWritableBridge.readInteger(18, __dbResults);
    this.pincode = JdbcWritableBridge.readInteger(19, __dbResults);
    this.aadharId = JdbcWritableBridge.readString(20, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.userId = JdbcWritableBridge.readLong(1, __dbResults);
    this.lmsUserId = JdbcWritableBridge.readLong(2, __dbResults);
    this.lmsName = JdbcWritableBridge.readString(3, __dbResults);
    this.orgName = JdbcWritableBridge.readString(4, __dbResults);
    this.name = JdbcWritableBridge.readString(5, __dbResults);
    this.gender = JdbcWritableBridge.readString(6, __dbResults);
    this.registrationDate = JdbcWritableBridge.readDate(7, __dbResults);
    this.emailId = JdbcWritableBridge.readString(8, __dbResults);
    this.mothertounge = JdbcWritableBridge.readString(9, __dbResults);
    this.highestEduDegree = JdbcWritableBridge.readString(10, __dbResults);
    this.goals = JdbcWritableBridge.readString(11, __dbResults);
    this.city = JdbcWritableBridge.readString(12, __dbResults);
    this.state = JdbcWritableBridge.readString(13, __dbResults);
    this.active = JdbcWritableBridge.readInteger(14, __dbResults);
    this.firstAccesDate = JdbcWritableBridge.readTimestamp(15, __dbResults);
    this.lastAccessDate = JdbcWritableBridge.readTimestamp(16, __dbResults);
    this.allowCert = JdbcWritableBridge.readInteger(17, __dbResults);
    this.yearOfBirth = JdbcWritableBridge.readInteger(18, __dbResults);
    this.pincode = JdbcWritableBridge.readInteger(19, __dbResults);
    this.aadharId = JdbcWritableBridge.readString(20, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(userId, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(lmsUserId, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(lmsName, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(orgName, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(gender, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(registrationDate, 7 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(emailId, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(mothertounge, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(highestEduDegree, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(goals, 11 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(city, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(state, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(active, 14 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(firstAccesDate, 15 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(lastAccessDate, 16 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(allowCert, 17 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(yearOfBirth, 18 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(pincode, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(aadharId, 20 + __off, 12, __dbStmt);
    return 20;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(userId, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(lmsUserId, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(lmsName, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(orgName, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(gender, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(registrationDate, 7 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeString(emailId, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(mothertounge, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(highestEduDegree, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(goals, 11 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(city, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(state, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(active, 14 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(firstAccesDate, 15 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(lastAccessDate, 16 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(allowCert, 17 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(yearOfBirth, 18 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeInteger(pincode, 19 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(aadharId, 20 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.userId = null;
    } else {
    this.userId = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.lmsUserId = null;
    } else {
    this.lmsUserId = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.lmsName = null;
    } else {
    this.lmsName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.orgName = null;
    } else {
    this.orgName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.name = null;
    } else {
    this.name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.gender = null;
    } else {
    this.gender = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.registrationDate = null;
    } else {
    this.registrationDate = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.emailId = null;
    } else {
    this.emailId = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.mothertounge = null;
    } else {
    this.mothertounge = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.highestEduDegree = null;
    } else {
    this.highestEduDegree = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.goals = null;
    } else {
    this.goals = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.city = null;
    } else {
    this.city = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.state = null;
    } else {
    this.state = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.active = null;
    } else {
    this.active = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.firstAccesDate = null;
    } else {
    this.firstAccesDate = new Timestamp(__dataIn.readLong());
    this.firstAccesDate.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.lastAccessDate = null;
    } else {
    this.lastAccessDate = new Timestamp(__dataIn.readLong());
    this.lastAccessDate.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.allowCert = null;
    } else {
    this.allowCert = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.yearOfBirth = null;
    } else {
    this.yearOfBirth = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.pincode = null;
    } else {
    this.pincode = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.aadharId = null;
    } else {
    this.aadharId = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.userId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.userId);
    }
    if (null == this.lmsUserId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lmsUserId);
    }
    if (null == this.lmsName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, lmsName);
    }
    if (null == this.orgName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, orgName);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.registrationDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.registrationDate.getTime());
    }
    if (null == this.emailId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, emailId);
    }
    if (null == this.mothertounge) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, mothertounge);
    }
    if (null == this.highestEduDegree) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, highestEduDegree);
    }
    if (null == this.goals) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, goals);
    }
    if (null == this.city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, city);
    }
    if (null == this.state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, state);
    }
    if (null == this.active) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.active);
    }
    if (null == this.firstAccesDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.firstAccesDate.getTime());
    __dataOut.writeInt(this.firstAccesDate.getNanos());
    }
    if (null == this.lastAccessDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lastAccessDate.getTime());
    __dataOut.writeInt(this.lastAccessDate.getNanos());
    }
    if (null == this.allowCert) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.allowCert);
    }
    if (null == this.yearOfBirth) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.yearOfBirth);
    }
    if (null == this.pincode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pincode);
    }
    if (null == this.aadharId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, aadharId);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.userId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.userId);
    }
    if (null == this.lmsUserId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lmsUserId);
    }
    if (null == this.lmsName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, lmsName);
    }
    if (null == this.orgName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, orgName);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.registrationDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.registrationDate.getTime());
    }
    if (null == this.emailId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, emailId);
    }
    if (null == this.mothertounge) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, mothertounge);
    }
    if (null == this.highestEduDegree) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, highestEduDegree);
    }
    if (null == this.goals) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, goals);
    }
    if (null == this.city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, city);
    }
    if (null == this.state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, state);
    }
    if (null == this.active) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.active);
    }
    if (null == this.firstAccesDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.firstAccesDate.getTime());
    __dataOut.writeInt(this.firstAccesDate.getNanos());
    }
    if (null == this.lastAccessDate) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lastAccessDate.getTime());
    __dataOut.writeInt(this.lastAccessDate.getNanos());
    }
    if (null == this.allowCert) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.allowCert);
    }
    if (null == this.yearOfBirth) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.yearOfBirth);
    }
    if (null == this.pincode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.pincode);
    }
    if (null == this.aadharId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, aadharId);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(userId==null?"\\N":"" + userId, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lmsUserId==null?"\\N":"" + lmsUserId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(lmsName==null?"\\N":lmsName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(orgName==null?"\\N":orgName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(name==null?"\\N":name, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(gender==null?"\\N":gender, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registrationDate==null?"\\N":"" + registrationDate, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(emailId==null?"\\N":emailId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(mothertounge==null?"\\N":mothertounge, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(highestEduDegree==null?"\\N":highestEduDegree, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(goals==null?"\\N":goals, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(city==null?"\\N":city, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(state==null?"\\N":state, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(active==null?"\\N":"" + active, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(firstAccesDate==null?"\\N":"" + firstAccesDate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lastAccessDate==null?"\\N":"" + lastAccessDate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(allowCert==null?"\\N":"" + allowCert, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(yearOfBirth==null?"\\N":"" + yearOfBirth, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pincode==null?"\\N":"" + pincode, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(aadharId==null?"\\N":aadharId, " ", delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(userId==null?"\\N":"" + userId, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lmsUserId==null?"\\N":"" + lmsUserId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(lmsName==null?"\\N":lmsName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(orgName==null?"\\N":orgName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(name==null?"\\N":name, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(gender==null?"\\N":gender, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registrationDate==null?"\\N":"" + registrationDate, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(emailId==null?"\\N":emailId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(mothertounge==null?"\\N":mothertounge, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(highestEduDegree==null?"\\N":highestEduDegree, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(goals==null?"\\N":goals, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(city==null?"\\N":city, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(state==null?"\\N":state, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(active==null?"\\N":"" + active, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(firstAccesDate==null?"\\N":"" + firstAccesDate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lastAccessDate==null?"\\N":"" + lastAccessDate, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(allowCert==null?"\\N":"" + allowCert, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(yearOfBirth==null?"\\N":"" + yearOfBirth, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pincode==null?"\\N":"" + pincode, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(aadharId==null?"\\N":aadharId, " ", delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.userId = null; } else {
      this.userId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lmsUserId = null; } else {
      this.lmsUserId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.lmsName = null; } else {
      this.lmsName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.orgName = null; } else {
      this.orgName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.registrationDate = null; } else {
      this.registrationDate = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.emailId = null; } else {
      this.emailId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.mothertounge = null; } else {
      this.mothertounge = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.highestEduDegree = null; } else {
      this.highestEduDegree = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.goals = null; } else {
      this.goals = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.city = null; } else {
      this.city = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.state = null; } else {
      this.state = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.active = null; } else {
      this.active = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.firstAccesDate = null; } else {
      this.firstAccesDate = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lastAccessDate = null; } else {
      this.lastAccessDate = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.allowCert = null; } else {
      this.allowCert = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.yearOfBirth = null; } else {
      this.yearOfBirth = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pincode = null; } else {
      this.pincode = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.aadharId = null; } else {
      this.aadharId = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.userId = null; } else {
      this.userId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lmsUserId = null; } else {
      this.lmsUserId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.lmsName = null; } else {
      this.lmsName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.orgName = null; } else {
      this.orgName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.registrationDate = null; } else {
      this.registrationDate = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.emailId = null; } else {
      this.emailId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.mothertounge = null; } else {
      this.mothertounge = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.highestEduDegree = null; } else {
      this.highestEduDegree = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.goals = null; } else {
      this.goals = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.city = null; } else {
      this.city = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.state = null; } else {
      this.state = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.active = null; } else {
      this.active = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.firstAccesDate = null; } else {
      this.firstAccesDate = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lastAccessDate = null; } else {
      this.lastAccessDate = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.allowCert = null; } else {
      this.allowCert = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.yearOfBirth = null; } else {
      this.yearOfBirth = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pincode = null; } else {
      this.pincode = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.aadharId = null; } else {
      this.aadharId = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    UserProfile o = (UserProfile) super.clone();
    o.registrationDate = (o.registrationDate != null) ? (java.sql.Date) o.registrationDate.clone() : null;
    o.firstAccesDate = (o.firstAccesDate != null) ? (java.sql.Timestamp) o.firstAccesDate.clone() : null;
    o.lastAccessDate = (o.lastAccessDate != null) ? (java.sql.Timestamp) o.lastAccessDate.clone() : null;
    return o;
  }

  public void clone0(UserProfile o) throws CloneNotSupportedException {
    o.registrationDate = (o.registrationDate != null) ? (java.sql.Date) o.registrationDate.clone() : null;
    o.firstAccesDate = (o.firstAccesDate != null) ? (java.sql.Timestamp) o.firstAccesDate.clone() : null;
    o.lastAccessDate = (o.lastAccessDate != null) ? (java.sql.Timestamp) o.lastAccessDate.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("userId", this.userId);
    __sqoop$field_map.put("lmsUserId", this.lmsUserId);
    __sqoop$field_map.put("lmsName", this.lmsName);
    __sqoop$field_map.put("orgName", this.orgName);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("registrationDate", this.registrationDate);
    __sqoop$field_map.put("emailId", this.emailId);
    __sqoop$field_map.put("mothertounge", this.mothertounge);
    __sqoop$field_map.put("highestEduDegree", this.highestEduDegree);
    __sqoop$field_map.put("goals", this.goals);
    __sqoop$field_map.put("city", this.city);
    __sqoop$field_map.put("state", this.state);
    __sqoop$field_map.put("active", this.active);
    __sqoop$field_map.put("firstAccesDate", this.firstAccesDate);
    __sqoop$field_map.put("lastAccessDate", this.lastAccessDate);
    __sqoop$field_map.put("allowCert", this.allowCert);
    __sqoop$field_map.put("yearOfBirth", this.yearOfBirth);
    __sqoop$field_map.put("pincode", this.pincode);
    __sqoop$field_map.put("aadharId", this.aadharId);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("userId", this.userId);
    __sqoop$field_map.put("lmsUserId", this.lmsUserId);
    __sqoop$field_map.put("lmsName", this.lmsName);
    __sqoop$field_map.put("orgName", this.orgName);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("registrationDate", this.registrationDate);
    __sqoop$field_map.put("emailId", this.emailId);
    __sqoop$field_map.put("mothertounge", this.mothertounge);
    __sqoop$field_map.put("highestEduDegree", this.highestEduDegree);
    __sqoop$field_map.put("goals", this.goals);
    __sqoop$field_map.put("city", this.city);
    __sqoop$field_map.put("state", this.state);
    __sqoop$field_map.put("active", this.active);
    __sqoop$field_map.put("firstAccesDate", this.firstAccesDate);
    __sqoop$field_map.put("lastAccessDate", this.lastAccessDate);
    __sqoop$field_map.put("allowCert", this.allowCert);
    __sqoop$field_map.put("yearOfBirth", this.yearOfBirth);
    __sqoop$field_map.put("pincode", this.pincode);
    __sqoop$field_map.put("aadharId", this.aadharId);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("userId".equals(__fieldName)) {
      this.userId = (Long) __fieldVal;
    }
    else    if ("lmsUserId".equals(__fieldName)) {
      this.lmsUserId = (Long) __fieldVal;
    }
    else    if ("lmsName".equals(__fieldName)) {
      this.lmsName = (String) __fieldVal;
    }
    else    if ("orgName".equals(__fieldName)) {
      this.orgName = (String) __fieldVal;
    }
    else    if ("name".equals(__fieldName)) {
      this.name = (String) __fieldVal;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
    }
    else    if ("registrationDate".equals(__fieldName)) {
      this.registrationDate = (java.sql.Date) __fieldVal;
    }
    else    if ("emailId".equals(__fieldName)) {
      this.emailId = (String) __fieldVal;
    }
    else    if ("mothertounge".equals(__fieldName)) {
      this.mothertounge = (String) __fieldVal;
    }
    else    if ("highestEduDegree".equals(__fieldName)) {
      this.highestEduDegree = (String) __fieldVal;
    }
    else    if ("goals".equals(__fieldName)) {
      this.goals = (String) __fieldVal;
    }
    else    if ("city".equals(__fieldName)) {
      this.city = (String) __fieldVal;
    }
    else    if ("state".equals(__fieldName)) {
      this.state = (String) __fieldVal;
    }
    else    if ("active".equals(__fieldName)) {
      this.active = (Integer) __fieldVal;
    }
    else    if ("firstAccesDate".equals(__fieldName)) {
      this.firstAccesDate = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("lastAccessDate".equals(__fieldName)) {
      this.lastAccessDate = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("allowCert".equals(__fieldName)) {
      this.allowCert = (Integer) __fieldVal;
    }
    else    if ("yearOfBirth".equals(__fieldName)) {
      this.yearOfBirth = (Integer) __fieldVal;
    }
    else    if ("pincode".equals(__fieldName)) {
      this.pincode = (Integer) __fieldVal;
    }
    else    if ("aadharId".equals(__fieldName)) {
      this.aadharId = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("userId".equals(__fieldName)) {
      this.userId = (Long) __fieldVal;
      return true;
    }
    else    if ("lmsUserId".equals(__fieldName)) {
      this.lmsUserId = (Long) __fieldVal;
      return true;
    }
    else    if ("lmsName".equals(__fieldName)) {
      this.lmsName = (String) __fieldVal;
      return true;
    }
    else    if ("orgName".equals(__fieldName)) {
      this.orgName = (String) __fieldVal;
      return true;
    }
    else    if ("name".equals(__fieldName)) {
      this.name = (String) __fieldVal;
      return true;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
      return true;
    }
    else    if ("registrationDate".equals(__fieldName)) {
      this.registrationDate = (java.sql.Date) __fieldVal;
      return true;
    }
    else    if ("emailId".equals(__fieldName)) {
      this.emailId = (String) __fieldVal;
      return true;
    }
    else    if ("mothertounge".equals(__fieldName)) {
      this.mothertounge = (String) __fieldVal;
      return true;
    }
    else    if ("highestEduDegree".equals(__fieldName)) {
      this.highestEduDegree = (String) __fieldVal;
      return true;
    }
    else    if ("goals".equals(__fieldName)) {
      this.goals = (String) __fieldVal;
      return true;
    }
    else    if ("city".equals(__fieldName)) {
      this.city = (String) __fieldVal;
      return true;
    }
    else    if ("state".equals(__fieldName)) {
      this.state = (String) __fieldVal;
      return true;
    }
    else    if ("active".equals(__fieldName)) {
      this.active = (Integer) __fieldVal;
      return true;
    }
    else    if ("firstAccesDate".equals(__fieldName)) {
      this.firstAccesDate = (java.sql.Timestamp) __fieldVal;
      return true;
    }
    else    if ("lastAccessDate".equals(__fieldName)) {
      this.lastAccessDate = (java.sql.Timestamp) __fieldVal;
      return true;
    }
    else    if ("allowCert".equals(__fieldName)) {
      this.allowCert = (Integer) __fieldVal;
      return true;
    }
    else    if ("yearOfBirth".equals(__fieldName)) {
      this.yearOfBirth = (Integer) __fieldVal;
      return true;
    }
    else    if ("pincode".equals(__fieldName)) {
      this.pincode = (Integer) __fieldVal;
      return true;
    }
    else    if ("aadharId".equals(__fieldName)) {
      this.aadharId = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
