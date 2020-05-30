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
drop table if exists relationship_status cascade;
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
    profile_uuid uuid not null,
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
    profile_id bigint not null unique references profile (id),
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
    id bigserial primary key,
    profile_id_1 bigint not null references profile (id),
    profile_id_2 bigint not null references profile (id),
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table relationship_status (
    id bigserial primary key,
    profile_id bigint not null references profile (id),
    relationship_id bigint not null references relationship (id),
    silenced boolean not null,
    blocked boolean not null,
    expires timestamp,
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table relationship_request (
    id bigserial primary key,
    requester_profile_id bigint not null references profile (id),
    requested_profile_id bigint references profile (id),
    request_uuid uuid not null,
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
    within_area geometry,
    beyond_area geometry,
    within_current_location_meters decimal(12,4),
    beyond_current_location_meters decimal(12,4),
    created_at timestamp not null,
    updated_at timestamp not null,
    version bigint
);

create table flare (
    id bigserial primary key,
    flare_uuid uuid not null unique,
    profile_id bigint not null references profile (id),
    flare_location geometry,
    flare_destination geometry,
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