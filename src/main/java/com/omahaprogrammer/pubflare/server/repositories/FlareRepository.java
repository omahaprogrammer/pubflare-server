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

package com.omahaprogrammer.pubflare.server.repositories;

import com.omahaprogrammer.pubflare.server.model.jpa.FlareEntity;
import com.omahaprogrammer.pubflare.server.model.jpa.ProfileEntity;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlareRepository extends JpaRepository<FlareEntity, Integer> {
    Optional<FlareEntity> findByFlareUuid(UUID uuid);

    @Query(value = "select new com.omahaprogrammer.pubflare.server.model.jpa.ActiveFlare(f, get_flare_pref(p.profileUuid, f.flareUuid, ?2, ?3, ?4)) from ProfileEntity p join p.friends fr join fr.flares f where p.profileUuid = ?1 and f.expires > ?4")
    List<Object[]> findFlares(UUID userUUID, double latitude, double longitude, Instant now);


}
