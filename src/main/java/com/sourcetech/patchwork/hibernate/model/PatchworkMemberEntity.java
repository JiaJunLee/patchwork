package com.sourcetech.patchwork.hibernate.model;

import javax.persistence.*;

/**
 * Created by 李佳骏 on 2017/4/11.
 */
@Entity
@Table(name = "patchwork_member", schema = "patchwork_database", catalog = "")
public class PatchworkMemberEntity {
    private int mId;
    private String mHashPassword;
    private String mPasswordSalt;
    private String mEmail;
    private String mPhoneNumber;

    @Id
    @Column(name = "m_id")
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Basic
    @Column(name = "m_hash_password")
    public String getmHashPassword() {
        return mHashPassword;
    }

    public void setmHashPassword(String mHashPassword) {
        this.mHashPassword = mHashPassword;
    }

    @Basic
    @Column(name = "m_password_salt")
    public String getmPasswordSalt() {
        return mPasswordSalt;
    }

    public void setmPasswordSalt(String mPasswordSalt) {
        this.mPasswordSalt = mPasswordSalt;
    }

    @Basic
    @Column(name = "m_email")
    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Basic
    @Column(name = "m_phone_number")
    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatchworkMemberEntity that = (PatchworkMemberEntity) o;

        if (mId != that.mId) return false;
        if (mHashPassword != null ? !mHashPassword.equals(that.mHashPassword) : that.mHashPassword != null)
            return false;
        if (mPasswordSalt != null ? !mPasswordSalt.equals(that.mPasswordSalt) : that.mPasswordSalt != null)
            return false;
        if (mEmail != null ? !mEmail.equals(that.mEmail) : that.mEmail != null) return false;
        if (mPhoneNumber != null ? !mPhoneNumber.equals(that.mPhoneNumber) : that.mPhoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mHashPassword != null ? mHashPassword.hashCode() : 0);
        result = 31 * result + (mPasswordSalt != null ? mPasswordSalt.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mPhoneNumber != null ? mPhoneNumber.hashCode() : 0);
        return result;
    }
}
