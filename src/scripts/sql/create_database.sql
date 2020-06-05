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

drop table if exists relationship_request cascade;
drop table if exists relationship cascade;
drop table if exists flare_preference cascade;
drop table if exists flare_response cascade;
drop table if exists flare cascade;

drop table if exists profile_username cascade;
drop table if exists profile_pictures cascade;
drop table if exists profile_website cascade;
drop table if exists profile_email cascade;
drop table if exists profile_phone cascade;
drop table if exists profile_address cascade;
drop table if exists profile_gender cascade;
drop table if exists profile_birth_date cascade;
drop table if exists profile cascade;

drop table if exists persistent_logins cascade;
drop table if exists authorities cascade;
drop table if exists users cascade;
drop table if exists acl_entry cascade;
drop table if exists acl_object_identity cascade;
drop table if exists acl_class cascade;
drop table if exists acl_sid cascade;

create table users (
    username text primary key,
    password text not null,
    enabled boolean not null
);

create table authorities (
    username text references users (username),
    authority text,
    primary key (username, authority)
);

create table persistent_logins (
    series text primary key,
    username text not null,
    token text not null,
    last_used timestamp not null
);

create table acl_sid (
    id bigserial primary key,
    principal boolean not null,
    sid text not null,
    unique(sid,principal)
);

create table acl_class (
    id bigserial primary key,
    class text unique
);

create table acl_object_identity (
    id bigserial primary key,
    object_id_class bigint not null references acl_class (id),
    object_id_identity text not null,
    parent_object bigint references acl_object_identity (id),
    owner_sid bigint references acl_sid (id),
    entries_inheriting boolean not null,
    unique(object_id_class,object_id_identity)
);

create table acl_entry (
    id bigserial primary key,
    acl_object_identity bigint not null references  acl_object_identity (id),
    ace_order int not null,
    sid bigint not null references acl_sid (id),
    mask int not null,
    granting boolean not null,
    audit_success boolean not null,
    audit_failure boolean not null,
    unique(acl_object_identity,ace_order)
);

create table profile (
    id bigserial primary key,
    profile_uuid uuid unique not null,
    full_name text,
    given_name text,
    family_name text,
    middle_name text,
    nickname text,
    zone_info text,
    locale text,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_username (
    id bigserial primary key,
    profile_id bigint unique not null references profile (id),
    username text references users (username),
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint,
    unique (profile_id, username)
);

create table profile_pictures (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    picture_date bytea not null,
    main_picture boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_website (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    website text not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_email (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    email_type text,
    email text not null,
    email_verified boolean not null,
    main_email boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_phone (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    phone_type text,
    phone text not null,
    phone_verified boolean not null,
    main_phone boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_address (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    address_type text,
    street_address text,
    locality text,
    region text,
    postal_code text,
    country text,
    main_address boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint,
    check (coalesce(street_address, locality, region, postal_code, country) is not null)
);

create table profile_gender (
    id bigserial primary key,
    profile_id bigint not null unique references profile (id),
    gender text not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table profile_birth_date (
    id bigserial primary key,
    profile_id bigint not null unique references profile (id),
    birth_date date not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table relationship (
    relationship_from bigint not null references profile (id),
    relationship_to bigint not null references profile (id)
);

create table relationship_request (
    id bigserial primary key,
    requester_profile_id bigint not null references profile (id),
    requested_profile_id bigint references profile (id),
    request_uuid uuid unique not null,
    expires timestamp,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table flare_preference (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    flare_preference_type text not null,
    start_time time,
    end_time time,
    self_within_area geography,
    self_beyond_area geography,
    flare_within_area geography,
    flare_beyond_area geography,
    within_current_location_meters decimal(12,4),
    beyond_current_location_meters decimal(12,4),
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint,
    check ((start_time is null and end_time is null) or (start_time is not null and end_time is not null and start_time <> end_time)),
    check ((self_within_area is null and self_beyond_area is null) or (self_within_area is not null and self_beyond_area is null) or (self_within_area is null and self_beyond_area is not null)),
    check ((flare_within_area is null and flare_beyond_area is null) or (flare_within_area is not null and flare_beyond_area is null) or (flare_within_area is null and flare_beyond_area is not null)),
    check ((within_current_location_meters is null and beyond_current_location_meters is null) or (within_current_location_meters is not null and within_current_location_meters > 0 and beyond_current_location_meters is null) or (within_current_location_meters is null and beyond_current_location_meters is not null and beyond_current_location_meters > 0))
);

create table flare (
    id bigserial primary key,
    flare_uuid uuid unique not null,
    profile_id bigint not null references profile (id),
    flare_location geography,
    flare_destination geography,
    already_there boolean not null,
    shareable boolean not null,
    expires timestamp,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table flare_response (
    id bigserial primary key,
    flare_id bigint not null references flare (id),
    respondent_profile_id bigint not null references profile (id),
    response text not null,
    message text,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create or replace function is_flare_pref_active(pref flare_preference, fl flare, curr_loc geography, now timestamp)
returns boolean
as $$
declare
	loc_circle geography;
begin
	if pref.start_time is not null then
		if pref.start_time < pref.end_time and now::time between pref.start_time and pref.end_time then
			return true;
		elsif pref.start_time > pref.end_time and now::time not between pref.end_time and pref.start_time then
			return true;
		else
			return false;
		end if;
    elsif pref.self_within_area is not null and st_covers(pref.self_within_area, curr_loc) then
        return true;
    elsif pref.self_beyond_area is not null and not st_covers(pref.self_beyond_area, curr_loc) then
        return true;
	elsif pref.flare_within_area is not null and st_covers(pref.flare_within_area, fl.flare_destination) then
		return true;
	elsif pref.flare_beyond_area is not null and not st_covers(pref.flare_beyond_area, fl.flare_destination) then
		return true;
	elsif pref.within_current_location_meters is not null then
		loc_circle := st_transform(st_buffer(st_transform(curr_loc, 4978), pref.within_current_location_meters),4326);
		return st_covers(loc_circle, fl.flare_destination);
	elsif pref.beyond_current_location_meters is not null then
		loc_circle := st_transform(st_buffer(st_transform(curr_loc, 4978), pref.beyond_current_location_meters),4326);
		return not st_covers(loc_circle, fl.flare_destination);
	else
		return false;
	end if;
end
$$ language plpgsql;

create or replace function get_flare_pref(param_profile_uuid profile.profile_uuid%type, param_flare_uuid flare.flare_uuid%type, curr_lat double precision, curr_long double precision, now timestamp)
    returns text
as $$
declare
    curr_loc geography := st_setsrid(st_makepoint(curr_long, curr_lat), 4326)::geography;
    flare_record flare%rowtype;
    prefs cursor for
        select *
        from flare_preference a
        where a.profile_id = (select id from profile where profile_uuid = param_profile_uuid)
        order by
            case flare_preference_type
                when 'IGNORE' then 1
                when 'SILENT' then 2
                when 'ALERT' then 3
                else 999 end;
begin
    select * into flare_record from flare where flare_uuid = param_flare_uuid;
    for pref in prefs loop
        if is_flare_pref_active(pref, flare_record, curr_loc, now) then
            return pref.flare_preference_type;
        end if;
    end loop;
end
$$ language plpgsql;
