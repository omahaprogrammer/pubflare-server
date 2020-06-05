package com.omahaprogrammer.pubflare.server.model.jpa;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.postgresql.geometric.PGpoint;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "flare", schema = "public", catalog = "pubflare")
public class FlareEntity {
    private long id;
    private UUID flareUuid;
    private Geometry<G2D> flareLocation;
    private Geometry<G2D> flareDestination;
    private boolean alreadyThere;
    private boolean shareable;
    private Instant expires;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private ProfileEntity profile;
    private List<FlareResponseEntity> responses;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flare_uuid")
    public UUID getFlareUuid() {
        return flareUuid;
    }

    public void setFlareUuid(UUID flareUuid) {
        this.flareUuid = flareUuid;
    }

    @Basic
    @Column(name = "flare_location")
    public Geometry<G2D> getFlareLocation() {
        return flareLocation;
    }

    public void setFlareLocation(Geometry<G2D> flareLocation) {
        this.flareLocation = flareLocation;
    }

    @Basic
    @Column(name = "flare_destination")
    public Geometry<G2D> getFlareDestination() {
        return flareDestination;
    }

    public void setFlareDestination(Geometry<G2D> flareDestination) {
        this.flareDestination = flareDestination;
    }

    @Basic
    @Column(name = "already_there")
    public boolean isAlreadyThere() {
        return alreadyThere;
    }

    public void setAlreadyThere(boolean alreadyThere) {
        this.alreadyThere = alreadyThere;
    }

    @Basic
    @Column(name = "shareable")
    public boolean isShareable() {
        return shareable;
    }

    public void setShareable(boolean shareable) {
        this.shareable = shareable;
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
        FlareEntity that = (FlareEntity) o;
        return id == that.id &&
                alreadyThere == that.alreadyThere &&
                shareable == that.shareable &&
                Objects.equals(flareUuid, that.flareUuid) &&
                Objects.equals(flareLocation, that.flareLocation) &&
                Objects.equals(flareDestination, that.flareDestination) &&
                Objects.equals(expires, that.expires) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flareUuid, flareLocation, flareDestination, alreadyThere, shareable, expires, createdAt, updatedAt, version);
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @OneToMany(mappedBy = "flare")
    public List<FlareResponseEntity> getResponses() {
        return responses;
    }

    public void setResponses(List<FlareResponseEntity> responses) {
        this.responses = responses;
    }
}
