package com.omahaprogrammer.pubflare.server.repositories;

import com.omahaprogrammer.pubflare.server.model.jpa.FlarePreferenceEntity;
import com.omahaprogrammer.pubflare.server.model.jpa.FlarePreferenceType;
import com.omahaprogrammer.pubflare.server.model.jpa.ProfileEntity;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public interface FlarePreferenceRepository extends JpaRepository<FlarePreferenceEntity, Integer> {
    FlarePreferenceEntity getPreferenceByLocation(ProfileEntity p, Point<?> location);
}
