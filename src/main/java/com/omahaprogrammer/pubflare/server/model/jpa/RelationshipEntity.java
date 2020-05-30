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
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "relationship", schema = "public", catalog = "pubflare")
public class RelationshipEntity {
    private long id;
    private long profileId1;
    private long profileId2;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private ProfileEntity profile1;
    private ProfileEntity profile2;
    private RelationshipStatusEntity status;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "profile_id_1")
    public long getProfileId1() {
        return profileId1;
    }

    public void setProfileId1(long profileId1) {
        this.profileId1 = profileId1;
    }

    @Basic
    @Column(name = "profile_id_2")
    public long getProfileId2() {
        return profileId2;
    }

    public void setProfileId2(long profileId2) {
        this.profileId2 = profileId2;
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
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
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
        RelationshipEntity that = (RelationshipEntity) o;
        return id == that.id &&
                profileId1 == that.profileId1 &&
                profileId2 == that.profileId2 &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId1, profileId2, createdAt, updatedAt, version);
    }

    @ManyToOne
    @JoinColumn(name = "profile_id_1", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile1() {
        return profile1;
    }

    public void setProfile1(ProfileEntity profile1) {
        this.profile1 = profile1;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id_2", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile2() {
        return profile2;
    }

    public void setProfile2(ProfileEntity profile2) {
        this.profile2 = profile2;
    }

    @OneToOne(mappedBy = "relationship")
    public RelationshipStatusEntity getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatusEntity status) {
        this.status = status;
    }
}
