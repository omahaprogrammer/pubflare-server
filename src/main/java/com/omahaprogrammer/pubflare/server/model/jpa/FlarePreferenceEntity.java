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

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "flare_preference", schema = "public", catalog = "pubflare")
public class FlarePreferenceEntity {
    private long id;
    private long profileId;
    private FlarePreferenceType flarePreferenceType;
    private LocalTime startTime;
    private LocalTime endTime;
    private Geometry<G2D> withinArea;
    private Geometry<G2D> beyondArea;
    private BigDecimal withinCurrentLocationMeters;
    private BigDecimal beyondCurrentLocationMeters;
    private Instant createdAt;
    private Instant updatedAt;
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
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "flare_preference_type")
    @Enumerated(EnumType.STRING)
    public FlarePreferenceType getFlarePreferenceType() {
        return flarePreferenceType;
    }

    public void setFlarePreferenceType(FlarePreferenceType flarePreferenceType) {
        this.flarePreferenceType = flarePreferenceType;
    }

    @Basic
    @Column(name = "start_time")
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "within_area")
    public Geometry<G2D> getWithinArea() {
        return withinArea;
    }

    public void setWithinArea(Geometry<G2D> withinArea) {
        this.withinArea = withinArea;
    }

    @Column(name = "beyond_area")
    public Geometry<G2D> getBeyondArea() {
        return beyondArea;
    }

    public void setBeyondArea(Geometry<G2D> beyondArea) {
        this.beyondArea = beyondArea;
    }

    @Basic
    @Column(name = "within_current_location_meters")
    public BigDecimal getWithinCurrentLocationMeters() {
        return withinCurrentLocationMeters;
    }

    public void setWithinCurrentLocationMeters(BigDecimal withinCurrentLocationMeters) {
        this.withinCurrentLocationMeters = withinCurrentLocationMeters;
    }

    @Basic
    @Column(name = "beyond_current_location_meters")
    public BigDecimal getBeyondCurrentLocationMeters() {
        return beyondCurrentLocationMeters;
    }

    public void setBeyondCurrentLocationMeters(BigDecimal beyondCurrentLocationMeters) {
        this.beyondCurrentLocationMeters = beyondCurrentLocationMeters;
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
        FlarePreferenceEntity that = (FlarePreferenceEntity) o;
        return id == that.id &&
                profileId == that.profileId &&
                Objects.equals(flarePreferenceType, that.flarePreferenceType) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(withinArea, that.withinArea) &&
                Objects.equals(beyondArea, that.beyondArea) &&
                Objects.equals(withinCurrentLocationMeters, that.withinCurrentLocationMeters) &&
                Objects.equals(beyondCurrentLocationMeters, that.beyondCurrentLocationMeters) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, flarePreferenceType, startTime, endTime, withinArea, beyondArea, withinCurrentLocationMeters, beyondCurrentLocationMeters, createdAt, updatedAt, version);
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
