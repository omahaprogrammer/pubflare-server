/*
 * Copyright (c) 2020 Jonathan Paz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omahaprogrammer.pubflare.server.model.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "profile_email", schema = "public", catalog = "pubflare")
public class ProfileEmailEntity {
    private long id;
    private Instant profileId;
    private String emailType;
    private String email;
    private boolean emailVerified;
    private boolean mainEmail;
    private Instant createdAt;
    private Timestamp updatedAt;
    private Long version;
    private ProfileEntity profile;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "profile_id")
    public Instant getProfileId() {
        return profileId;
    }

    public void setProfileId(Instant profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "email_type")
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "email_verified")
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Basic
    @Column(name = "main_email")
    public boolean isMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(boolean mainEmail) {
        this.mainEmail = mainEmail;
    }

    @Basic
    @Column(name = "created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "version")
    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileEmailEntity that = (ProfileEmailEntity) o;
        return id == that.id &&
                emailVerified == that.emailVerified &&
                mainEmail == that.mainEmail &&
                Objects.equals(profileId, that.profileId) &&
                Objects.equals(emailType, that.emailType) &&
                Objects.equals(email, that.email) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, emailType, email, emailVerified, mainEmail, createdAt, updatedAt, version);
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
}
