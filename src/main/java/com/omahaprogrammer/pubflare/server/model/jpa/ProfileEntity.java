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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "profile", schema = "public", catalog = "pubflare")
public class ProfileEntity {
    private long id;
    private UUID profileUuid;
    private String fullName;
    private String givenName;
    private String familyName;
    private String middleName;
    private String nickname;
    private String zoneInfo;
    private String locale;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private List<FlareEntity> flares;
    private List<FlarePreferenceEntity> preferences;
    private List<ProfileAddressEntity> addresses;
    private ProfileBirthDateEntity birthDate;
    private List<ProfileEmailEntity> emails;
    private ProfileGenderEntity gender;
    private ProfilePhoneEntity phones;
    private List<ProfilePicturesEntity> pictures;
    private ProfileUsernameEntity username;
    private ProfileWebsiteEntity website;
    private List<RelationshipEntity> relationships1;
    private List<RelationshipEntity> relationships2;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "profile_uuid")
    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }

    @Basic
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "given_name")
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @Basic
    @Column(name = "family_name")
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "zone_info")
    public String getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(String zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    @Basic
    @Column(name = "locale")
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
        ProfileEntity that = (ProfileEntity) o;
        return id == that.id &&
                Objects.equals(profileUuid, that.profileUuid) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(givenName, that.givenName) &&
                Objects.equals(familyName, that.familyName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(zoneInfo, that.zoneInfo) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileUuid, fullName, givenName, familyName, middleName, nickname, zoneInfo, locale, createdAt, updatedAt, version);
    }

    @OneToMany(mappedBy = "profile")
    public List<FlareEntity> getFlares() {
        return flares;
    }

    public void setFlares(List<FlareEntity> flares) {
        this.flares = flares;
    }

    @OneToMany(mappedBy = "profile")
    public List<FlarePreferenceEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<FlarePreferenceEntity> preferences) {
        this.preferences = preferences;
    }

    @OneToMany(mappedBy = "profile")
    public List<ProfileAddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<ProfileAddressEntity> addresses) {
        this.addresses = addresses;
    }

    @OneToOne(mappedBy = "profile")
    public ProfileBirthDateEntity getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ProfileBirthDateEntity birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "profile")
    public List<ProfileEmailEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<ProfileEmailEntity> emails) {
        this.emails = emails;
    }

    @OneToOne(mappedBy = "profile")
    public ProfileGenderEntity getGender() {
        return gender;
    }

    public void setGender(ProfileGenderEntity gender) {
        this.gender = gender;
    }

    @OneToOne(mappedBy = "profile")
    public ProfilePhoneEntity getPhones() {
        return phones;
    }

    public void setPhones(ProfilePhoneEntity phones) {
        this.phones = phones;
    }

    @OneToMany(mappedBy = "profile")
    public List<ProfilePicturesEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<ProfilePicturesEntity> pictures) {
        this.pictures = pictures;
    }

    @OneToOne(mappedBy = "profile")
    public ProfileUsernameEntity getUsername() {
        return username;
    }

    public void setUsername(ProfileUsernameEntity userName) {
        this.username = userName;
    }

    @OneToOne(mappedBy = "profile")
    public ProfileWebsiteEntity getWebsite() {
        return website;
    }

    public void setWebsite(ProfileWebsiteEntity website) {
        this.website = website;
    }

    @OneToMany(mappedBy = "profile1")
    public List<RelationshipEntity> getRelationships1() {
        return relationships1;
    }

    public void setRelationships1(List<RelationshipEntity> relationships1) {
        this.relationships1 = relationships1;
    }

    @OneToMany(mappedBy = "profile2")
    public List<RelationshipEntity> getRelationships2() {
        return relationships2;
    }

    public void setRelationships2(List<RelationshipEntity> relationships2) {
        this.relationships2 = relationships2;
    }
}
