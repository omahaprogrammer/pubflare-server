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
import java.util.UUID;

@Entity
@Table(name = "relationship_request", schema = "public", catalog = "pubflare")
public class RelationshipRequestEntity {
    private long id;
    private long requesterProfileId;
    private Long requestedProfileId;
    private UUID requestUuid;
    private Instant expires;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private ProfileEntity requester;
    private ProfileEntity requested;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "requester_profile_id")
    public long getRequesterProfileId() {
        return requesterProfileId;
    }

    public void setRequesterProfileId(long requesterProfileId) {
        this.requesterProfileId = requesterProfileId;
    }

    @Basic
    @Column(name = "requested_profile_id")
    public Long getRequestedProfileId() {
        return requestedProfileId;
    }

    public void setRequestedProfileId(Long requestedProfileId) {
        this.requestedProfileId = requestedProfileId;
    }

    @Basic
    @Column(name = "request_uuid")
    public UUID getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(UUID requestUuid) {
        this.requestUuid = requestUuid;
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
        RelationshipRequestEntity that = (RelationshipRequestEntity) o;
        return id == that.id &&
                requesterProfileId == that.requesterProfileId &&
                Objects.equals(requestedProfileId, that.requestedProfileId) &&
                Objects.equals(requestUuid, that.requestUuid) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requesterProfileId, requestedProfileId, requestUuid, expires, createdAt, updatedAt, version);
    }

    @OneToOne
    @JoinColumn(name = "requester_profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getRequester() {
        return requester;
    }

    public void setRequester(ProfileEntity requester) {
        this.requester = requester;
    }

    @OneToOne
    @JoinColumn(name = "requested_profile_id", referencedColumnName = "id")
    public ProfileEntity getRequested() {
        return requested;
    }

    public void setRequested(ProfileEntity requested) {
        this.requested = requested;
    }
}
