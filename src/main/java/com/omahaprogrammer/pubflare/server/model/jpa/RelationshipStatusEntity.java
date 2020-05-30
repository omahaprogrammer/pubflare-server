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
@Table(name = "relationship_status", schema = "public", catalog = "pubflare")
public class RelationshipStatusEntity {
    private long id;
    private long profileId;
    private Instant relationshipId;
    private boolean silenced;
    private boolean blocked;
    private Instant expires;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private ProfileEntity profile;
    private RelationshipEntity relationship;

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
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "relationship_id")
    public Instant getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Instant relationshipId) {
        this.relationshipId = relationshipId;
    }

    @Basic
    @Column(name = "silenced")
    public boolean isSilenced() {
        return silenced;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }

    @Basic
    @Column(name = "blocked")
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Basic
    @Column(name = "expires")
    public Instant getExpires() {
        return expires;
    }

    public void setExpires(Instant expires) {
        this.expires = expires;
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
        RelationshipStatusEntity that = (RelationshipStatusEntity) o;
        return id == that.id &&
                profileId == that.profileId &&
                silenced == that.silenced &&
                blocked == that.blocked &&
                Objects.equals(relationshipId, that.relationshipId) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, relationshipId, silenced, blocked, expires, createdAt, updatedAt, version);
    }

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @OneToOne
    @JoinColumn(name = "relationship_id", referencedColumnName = "id", nullable = false)
    public RelationshipEntity getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipEntity relationship) {
        this.relationship = relationship;
    }
}
