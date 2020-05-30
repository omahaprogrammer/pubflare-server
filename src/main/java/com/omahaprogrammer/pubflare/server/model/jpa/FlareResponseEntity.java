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
@Table(name = "flare_response", schema = "public", catalog = "pubflare")
public class FlareResponseEntity {
    private long id;
    private long flareId;
    private long respondentProfileId;
    private String response;
    private String message;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private FlareEntity flare;
    private ProfileEntity respondent;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flare_id")
    public long getFlareId() {
        return flareId;
    }

    public void setFlareId(long flareId) {
        this.flareId = flareId;
    }

    @Basic
    @Column(name = "respondent_profile_id")
    public long getRespondentProfileId() {
        return respondentProfileId;
    }

    public void setRespondentProfileId(long respondentProfileId) {
        this.respondentProfileId = respondentProfileId;
    }

    @Basic
    @Column(name = "response")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        FlareResponseEntity that = (FlareResponseEntity) o;
        return id == that.id &&
                flareId == that.flareId &&
                respondentProfileId == that.respondentProfileId &&
                Objects.equals(response, that.response) &&
                Objects.equals(message, that.message) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flareId, respondentProfileId, response, message, createdAt, updatedAt, version);
    }

    @ManyToOne
    @JoinColumn(name = "flare_id", referencedColumnName = "id", nullable = false)
    public FlareEntity getFlare() {
        return flare;
    }

    public void setFlare(FlareEntity flare) {
        this.flare = flare;
    }

    @OneToOne
    @JoinColumn(name = "respondent_profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getRespondent() {
        return respondent;
    }

    public void setRespondent(ProfileEntity respondent) {
        this.respondent = respondent;
    }
}
