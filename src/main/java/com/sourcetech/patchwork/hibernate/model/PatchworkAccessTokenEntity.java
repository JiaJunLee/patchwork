package com.sourcetech.patchwork.hibernate.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
@Entity
@Table(name = "patchwork_access_token", schema = "patchwork_database", catalog = "")
public class PatchworkAccessTokenEntity {
    private int patchworkMemberMId;
    private String accessToken;
    private Timestamp deadline;

    @Basic
    @Column(name = "patchwork_member_m_id")
    public int getPatchworkMemberMId() {
        return patchworkMemberMId;
    }

    public void setPatchworkMemberMId(int patchworkMemberMId) {
        this.patchworkMemberMId = patchworkMemberMId;
    }

    @Id
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatchworkAccessTokenEntity that = (PatchworkAccessTokenEntity) o;

        if (patchworkMemberMId != that.patchworkMemberMId) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patchworkMemberMId;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }
}
