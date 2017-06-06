-- *******************************************************************************
-- Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
--
-- All rights reserved. This program and the accompanying materials
-- are made available under the terms of the Eclipse Public License v1.0
-- which accompanies this distribution, and is available at
-- http://www.eclipse.org/legal/epl-v10.html
--
-- Contributors:
--     Eurotech - initial API and implementation
-- *******************************************************************************

-- liquibase formatted sql

-- changeset configurarion:1

CREATE TABLE IF NOT EXISTS sys_configuration (
  scope_id          		 BIGINT(21) 	  UNSIGNED,
  id                         BIGINT(21) 	  UNSIGNED NOT NULL,
  pid						 VARCHAR(255) 	  NOT NULL,
  configurations			 TEXT,
  created_on                 TIMESTAMP(3) 	  DEFAULT 0,
  created_by                 BIGINT(21) 	  UNSIGNED NOT NULL,
  modified_on                TIMESTAMP(3) 	  NOT NULL,
  modified_by                BIGINT(21) 	  UNSIGNED NOT NULL,
  optlock                    INT UNSIGNED,
  attributes				 TEXT,
  properties                 TEXT,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX idx_configurationScopeId ON sys_configuration (scope_id);

CREATE TABLE athz_access_info (
  scope_id             	    BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on               TIMESTAMP(3)  NOT NULL,
  modified_by               BIGINT(21) 	  UNSIGNED NOT NULL,
  
  user_id					BIGINT(21) 	  UNSIGNED NOT NULL,
  
  optlock                   INT UNSIGNED,
  attributes				TEXT,
  properties                TEXT,
  
  PRIMARY KEY (id)
  
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_scopeId_userId ON athz_access_info (scope_id, user_id);

INSERT INTO athz_access_info
	VALUES
		(1, 1, NOW(), 1, NOW(), 1, 1, 0, '', ''),
		(1, 2, NOW(), 1, NOW(), 1, 2, 0, '', '');


CREATE TABLE athz_access_permission (
  scope_id             	    BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL, 
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  
  access_info_id			BIGINT(21) 	  UNSIGNED NOT NULL,
  
  domain					VARCHAR(64),
  action					VARCHAR(64),
  target_scope_id			BIGINT(21)	  UNSIGNED,
  group_id             	    BIGINT(21) 	  UNSIGNED,
  forwardable 				BOOLEAN       NOT NULL DEFAULT FALSE,
    
  PRIMARY KEY (id),
  
  FOREIGN KEY (access_info_id) REFERENCES athz_access_info(id) ON DELETE CASCADE
  
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_scopeId_accessId_domain_action_targetScopeId_groupId ON athz_access_permission (scope_id, access_info_id, domain, action, target_scope_id, group_id);

INSERT INTO athz_access_permission
	VALUES
		(1, 1, NOW(), 1, 2, 'broker', 'connect', 1, null, false); -- kapua-broker assigned of NOT forwardable permission: broker:connect:1:*
        


CREATE TABLE athz_role (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on               TIMESTAMP(3)  NOT NULL,
  modified_by               BIGINT(21) 	  UNSIGNED NOT NULL,

  name 						VARCHAR(255)  NOT NULL,
  
  optlock                   INT UNSIGNED,
  attributes				TEXT,
  properties                TEXT,
  
  PRIMARY KEY (id)

) DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_role_name ON athz_role (scope_id, name);

INSERT INTO athz_role
	VALUES
		(1, 1, NOW(), 1, NOW(), 1, 'admin', 0, '','');


CREATE TABLE athz_role_permission (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,

  role_id             	    BIGINT(21) 	  UNSIGNED,
  
  domain					VARCHAR(64),
  action					VARCHAR(64),
  target_scope_id		    BIGINT(21)	  UNSIGNED,
  group_id             	    BIGINT(21) 	  UNSIGNED,
  forwardable 				BOOLEAN 	  NOT NULL,
  
  PRIMARY KEY (id),
  
  FOREIGN KEY (role_id) REFERENCES athz_role(id) ON DELETE CASCADE
  
) DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_role_permission_scope_id ON athz_role_permission (role_id, domain, action, target_scope_id, group_id);

INSERT INTO athz_role_permission
	VALUES
		(1,  1, NOW(), 1, 1, null, null, null, null, true);


CREATE TABLE atht_access_token (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on            	TIMESTAMP(3),
  modified_by            	BIGINT(21)    UNSIGNED,
  
  user_id 					BIGINT(21) 	  UNSIGNED NOT NULL,
  token_id					TEXT	      NOT NULL,
  expires_on				TIMESTAMP(3)  NOT NULL,
  refresh_token				TEXT	      NOT NULL,
  refresh_expires_on		TIMESTAMP(3)  NOT NULL,
  invalidated_on			TIMESTAMP(3),
  
  optlock               	INT UNSIGNED,
  attributes             	TEXT,  
  properties             	TEXT,  
  
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_atht_access_token_scope_id ON atht_access_token (scope_id);
CREATE INDEX idx_atht_access_token_user_id ON atht_access_token (scope_id, user_id);


CREATE TABLE atht_credential (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on            	TIMESTAMP(3),
  modified_by            	BIGINT(21)    UNSIGNED,
  
  user_id 					BIGINT(21) 	  UNSIGNED NOT NULL,
  credential_type			VARCHAR(64)	  NOT NULL,
  credential_key			VARCHAR(255)  NOT NULL,
  
  optlock               	INT UNSIGNED,
  attributes             	TEXT,  
  properties             	TEXT,  
  
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_atht_credential_scope_id ON atht_credential (scope_id);
CREATE INDEX idx_atht_credential_user_id ON atht_credential (scope_id, user_id);
CREATE INDEX idx_atht_credential_type_credential_key ON atht_credential (credential_type, credential_key);

INSERT INTO atht_credential (`scope_id`, `id`, `created_on`, `created_by`, `modified_on`, `modified_by`, `user_id`, `credential_type`, `credential_key`, `optlock`)
		VALUES ('1', '1', CURRENT_TIMESTAMP(), '1', CURRENT_TIMESTAMP(), '1', '1', 'PASSWORD', '$2a$12$BjLeC/gqcnEyk.XNo2qorul.a/v4HDuOUlfmojdSZXRSFTjymPdVm', '0'), -- Clear text value: kapua-password
			   ('1', '2', CURRENT_TIMESTAMP(), '1', CURRENT_TIMESTAMP(), '1', '1', 'API_KEY', '12345678:$2a$12$BjLeC/gqcnEyk.XNo2qorul.a/v4HDuOUlfmojdSZXRSFTjymPdVm', '0'), -- Clear text value: 12345678kapua-password
			   ('1', '3', CURRENT_TIMESTAMP(), '1', CURRENT_TIMESTAMP(), '1', '2', 'PASSWORD', '$2a$12$BjLeC/gqcnEyk.XNo2qorul.a/v4HDuOUlfmojdSZXRSFTjymPdVm', '0'); -- Clear text value: kapua-password


CREATE TABLE athz_group (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on               TIMESTAMP(3)  NOT NULL,
  modified_by               BIGINT(21) 	  UNSIGNED NOT NULL,

  name 						VARCHAR(255)  NOT NULL,
  
  optlock                   INT UNSIGNED,
  attributes				TEXT,
  properties                TEXT,
  
  PRIMARY KEY (id)

) DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_group_name ON athz_group (scope_id, name);


CREATE TABLE athz_access_role (
  scope_id             	    BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL, 
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  
  access_info_id			BIGINT(21) 	  UNSIGNED NOT NULL,
  role_id					BIGINT(21) 	  UNSIGNED NOT NULL,
    
  PRIMARY KEY (id),
  
  FOREIGN KEY (access_info_id) REFERENCES athz_access_info(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES athz_role(id) ON DELETE CASCADE

) DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_scopeId_accessId_roleId ON athz_access_role (scope_id, access_info_id, role_id);

INSERT INTO athz_access_role
	VALUES
		(1, 1, NOW(), 1, 1, 1); -- kapua-sys assigned of role admin

INSERT INTO sys_configuration (
  SCOPE_ID,
  ID,
  PID,
  CONFIGURATIONS,
  CREATED_ON,
  CREATED_BY,
  MODIFIED_ON,
  MODIFIED_BY,
  OPTLOCK,
  ATTRIBUTES,
  PROPERTIES)
VALUES (1,
        5,
        'org.eclipse.kapua.service.authorization.role.RoleService',
        CONCAT('#', CURRENT_TIMESTAMP(), CHAR(13), CHAR(10),
        'maxNumberChildEntities=0', CHAR(13), CHAR(10),
        'infiniteChildEntities=true'),
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP(),
  1,
  0,
  null,
  null);


CREATE TABLE athz_domain (
  scope_id             		BIGINT(21) 	  UNSIGNED,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,

  name 						VARCHAR(255)  NOT NULL,
  serviceName 				VARCHAR(1023)  NOT NULL,
    
  PRIMARY KEY (id)

) DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX idx_domain_name ON athz_domain (name);

INSERT INTO athz_domain
	VALUES
		(NULL,  1, NOW(), 1, 'account',				'accountService'),
		(NULL,  2, NOW(), 1, 'broker',				'brokerService'),
		(NULL,  3, NOW(), 1, 'credential',			'credentialService'),
		(NULL,  4, NOW(), 1, 'device',				'deviceService'),
		(NULL,  5, NOW(), 1, 'device_connection',	'deviceConnectionService'),
		(NULL,  6, NOW(), 1, 'device_management',	'deviceManagementService'),
		(NULL,  7, NOW(), 1, 'device_event',		'deviceEventService'),
		(NULL,  8, NOW(), 1, 'device_lifecycle',	'deviceLifecycleService'),
		(NULL,  9, NOW(), 1, 'datastore',			'datastoreService'),
		(NULL, 10, NOW(), 1, 'domain',				'domainService'),
		(NULL, 11, NOW(), 1, 'group',				'groupService'),
		(NULL, 12, NOW(), 1, 'access_info',			'accessInfoService'),
		(NULL, 13, NOW(), 1, 'access_token',		'accessTokenService'),
		(NULL, 14, NOW(), 1, 'role',				'roleService'),
		(NULL, 15, NOW(), 1, 'user',				'userService');
        

CREATE TABLE athz_domain_actions (
  domain_id                 BIGINT(21) 	  UNSIGNED NOT NULL,
  action					VARCHAR(255)  NOT NULL,
  
  PRIMARY KEY (domain_id, action),
  
  FOREIGN KEY (domain_id) REFERENCES athz_domain(id) ON DELETE CASCADE
 
) DEFAULT CHARSET=utf8;

INSERT INTO athz_domain_actions
	VALUES
		(1, 'read'),
		(1, 'write'),
		(1, 'delete'),

		(2, 'connect'),

		(3, 'read'),
		(3, 'write'),
		(3, 'delete'),

		(4, 'read'),
		(4, 'write'),
		(4, 'delete'),

		(5, 'read'),
		(5, 'write'),
		(5, 'delete'),

		(6, 'read'),
		(6, 'write'),
		(6, 'delete'),
		(6, 'execute'),
		(6, 'connect'),

		(7, 'read'),
		(7, 'write'),
		(7, 'delete'),

		(8, 'read'),
		(8, 'write'),
		(8, 'delete'),

		(9, 'read'),
		(9, 'write'),
		(9, 'delete'),

		(10, 'read'),
		(10, 'write'),
		(10, 'delete'),

		(11, 'read'),
		(11, 'write'),
		(11, 'delete'),

		(12, 'read'),
		(12, 'write'),
		(12, 'delete'),

		(13, 'read'),
		(13, 'write'),
		(13, 'delete'),

		(14, 'read'),
		(14, 'write'),
		(14, 'delete'),

		(15, 'read'),
		(15, 'write'),
		(15, 'delete');


INSERT INTO sys_configuration (
  SCOPE_ID,
  ID,
  PID,
  CONFIGURATIONS,
  CREATED_ON,
  CREATED_BY,
  MODIFIED_ON,
  MODIFIED_BY,
  OPTLOCK,
  ATTRIBUTES,
  PROPERTIES)
VALUES (1,
        4,
        'org.eclipse.kapua.service.authorization.group.GroupService',
        CONCAT('#', CURRENT_TIMESTAMP(), CHAR(13), CHAR(10),
        'maxNumberChildEntities=0', CHAR(13), CHAR(10),
        'infiniteChildEntities=true'),
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP(),
  1,
  0,
  null,
  null);


INSERT INTO sys_configuration (
	SCOPE_ID,
    ID,
    PID,
    CONFIGURATIONS,
    CREATED_ON,
    CREATED_BY,
    MODIFIED_ON,
    MODIFIED_BY,
    OPTLOCK,
    ATTRIBUTES,
    PROPERTIES)
VALUES (1,
        1,
        'org.eclipse.kapua.service.account.AccountService',
        CONCAT('#', CURRENT_TIMESTAMP(), CHAR(13), CHAR(10),
               'maxNumberChildEntities=0', CHAR(13), CHAR(10),
               'infiniteChildEntities=true'),
        CURRENT_TIMESTAMP(),
        1,
        CURRENT_TIMESTAMP(),
        1,
        0,
        null,
        null);



CREATE TABLE act_account (
  scope_id          		 BIGINT(21) 	  UNSIGNED,
  id                         BIGINT(21) 	  UNSIGNED NOT NULL,
  name                       VARCHAR(255) 	  NOT NULL,
  created_on                 TIMESTAMP(3) 	  DEFAULT 0,
  created_by                 BIGINT(21) 	  UNSIGNED NOT NULL,
  modified_on                TIMESTAMP(3)     NOT NULL,
  modified_by                BIGINT(21) 	  UNSIGNED NOT NULL,
  
  org_name                   VARCHAR(255) 	  NOT NULL,
  org_person_name            VARCHAR(255),
  org_email                  VARCHAR(255) 	  NOT NULL,
  org_phone_number           VARCHAR(64),
  org_address_line_1         VARCHAR(255),
  org_address_line_2         VARCHAR(255),
  org_address_line_3         VARCHAR(255),
  org_zip_postcode           VARCHAR(255),
  org_city                   VARCHAR(255),
  org_state_province_county  VARCHAR(255),
  org_country                VARCHAR(255),
  
  parent_account_path        VARCHAR(64),
  optlock                    INT UNSIGNED,
  attributes				 TEXT,
  properties                 TEXT,
  
  PRIMARY KEY (id),
  FOREIGN KEY (scope_id) REFERENCES act_account(id) ON DELETE RESTRICT,
  CONSTRAINT act_accountName UNIQUE (name)
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_account_scope_id ON act_account (scope_id);

INSERT INTO `act_account` (
	`scope_id`,
	`id`,
	`name`,
	`created_on`,
	`created_by`,
	`modified_on`,
	`modified_by`,
	`org_name`,
	`org_person_name`,
	`org_email`,
	`org_phone_number`,
	`org_address_line_1`,
	`org_address_line_2`,
	`org_address_line_3`,
	`org_zip_postcode`,
	`org_city`,
	`org_state_province_county`,
	`org_country`,
	`parent_account_path`,
	`optlock`,
	`attributes`,
	`properties`)
VALUES (NULL,
		1,
		'kapua-sys',
		CURRENT_TIMESTAMP(),
		1,
		CURRENT_TIMESTAMP(),
		1,
		'kapua-org',
		'Kapua Sysadmin',
		'kapua-sys@eclipse.org',
		'+1 555 123 4567',
		NULL,
		NULL,
		NULL,
		NULL,
		NULL,
		NULL,
        NULL,
		'/1',
		0,
		NULL,
		NULL);



CREATE TABLE tst_liquibase (
  id                         BIGINT(21) 	  UNSIGNED NOT NULL,

  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;



CREATE TABLE usr_user (
  scope_id             		BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  name               	    VARCHAR(255)  NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on            	TIMESTAMP(3),
  modified_by            	BIGINT(21)    UNSIGNED,
  status                 	VARCHAR(64)   NOT NULL DEFAULT 'ENABLED',
  display_name           	VARCHAR(255),
  email                  	VARCHAR(255),
  phone_number           	VARCHAR(64),
  user_type					VARCHAR(64)   NOT NULL DEFAULT 'INTERNAL',
  external_id				VARCHAR(255),
  optlock               	INT UNSIGNED,
  attributes             	TEXT,  
  properties             	TEXT,  

  PRIMARY KEY (id),
  CONSTRAINT usr_uc_name UNIQUE (name)
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_user_scope_id ON usr_user (scope_id);

INSERT INTO `usr_user` (`scope_id`, `id`, `name`, `created_on`, `created_by`, `modified_on`, `modified_by`, `status`, `display_name`, `email`, `phone_number`, `user_type`, `external_id`, `optlock`, `attributes`, `properties`)
		VALUES (1, 1, 'kapua-sys',    CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP(), 1, 'ENABLED', 'Kapua Sysadmin', 'kapua-sys@eclipse.org',    '+1 555 123 4567', 'INTERNAL', NULL, 0, NULL, NULL),
		       (1, 2, 'kapua-broker', CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP(), 1, 'ENABLED', 'Kapua Broker',   'kapua-broker@eclipse.org', '+1 555 123 4567', 'INTERNAL', NULL, 0, NULL, NULL);



INSERT INTO sys_configuration (
  SCOPE_ID,
  ID,
  PID,
  CONFIGURATIONS,
  CREATED_ON,
  CREATED_BY,
  MODIFIED_ON,
  MODIFIED_BY,
  OPTLOCK,
  ATTRIBUTES,
  PROPERTIES)
VALUES (1,
        2,
        'org.eclipse.kapua.service.user.UserService',
        CONCAT('#', CURRENT_TIMESTAMP(), CHAR(13), CHAR(10),
        'maxNumberChildEntities=0', CHAR(13), CHAR(10),
        'infiniteChildEntities=true'),
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP(),
  1,
  0,
  null,
  null);

CREATE TABLE dvc_device_connection (
  scope_id             	    BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  modified_on            	TIMESTAMP(3)  NOT NULL,
  modified_by            	BIGINT(21)    UNSIGNED NOT NULL, 
  
  status				    VARCHAR(20)   NOT NULL,
  client_id					VARCHAR(255)  NOT NULL,
  user_id        			BIGINT(21)    UNSIGNED NOT NULL,  
  protocol       			VARCHAR(64),
  client_ip      			VARCHAR(255),
  server_ip      			VARCHAR(255), 
  
  optlock                   INT UNSIGNED,
  attributes				 TEXT,
  properties                 TEXT,

  PRIMARY KEY (scope_id, id)   -- primary key needs to include the partitioning key
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_connection_status_id_client_id ON dvc_device_connection (scope_id, id, status, client_id);
CREATE INDEX idx_connection_client_id_status_id ON dvc_device_connection (scope_id, id, client_id, status);


CREATE TABLE dvc_device (
  scope_id             	    BIGINT(21) 	    UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	    UNSIGNED NOT NULL,
  
  group_id             	    BIGINT(21) 	    UNSIGNED,
  
  created_on             	TIMESTAMP(3)    NULL,
  created_by             	BIGINT(21)      UNSIGNED NOT NULL,
  modified_on            	TIMESTAMP       NULL,
  modified_by            	BIGINT(21)      UNSIGNED NOT NULL,
  
  client_id                 VARCHAR(255)    NOT NULL,
  connection_id             BIGINT(21) 	    UNSIGNED NULL,
  last_event_id             BIGINT(21) 	    UNSIGNED NULL,
  
  status                 	VARCHAR(64)     NOT NULL DEFAULT 'ENABLED',
  display_name              VARCHAR(255), 
  serial_number             VARCHAR(255),
  model_id                  VARCHAR(255),
  imei                      VARCHAR(24),
  imsi                      VARCHAR(15),
  iccid                     VARCHAR(22),
  bios_version              VARCHAR(255),
  firmware_version          VARCHAR(255),
  os_version                VARCHAR(255),
  jvm_version               VARCHAR(255),
  osgi_framework_version    VARCHAR(255),
  app_framework_version     VARCHAR(255),
  app_identifiers           VARCHAR(1024),
  accept_encoding           VARCHAR(255),
  gps_longitude             DECIMAL(11,8),
  gps_latitude              DECIMAL(11,8),
  custom_attribute_1        VARCHAR(255),
  custom_attribute_2        VARCHAR(255),
  custom_attribute_3        VARCHAR(255),
  custom_attribute_4        VARCHAR(255),
  custom_attribute_5        VARCHAR(255),
  credentials_mode          VARCHAR(64)   NOT NULL,
  preferred_user_id			BIGINT(21)    DEFAULT 0,
  
  optlock                   INT UNSIGNED,
  attributes             	TEXT,  
  properties             	TEXT,   
  
  PRIMARY KEY (scope_id, id),   -- primary key needs to include the partitioning key
  CONSTRAINT uc_clientId UNIQUE (scope_id, client_id),
  CONSTRAINT uc_imei UNIQUE (scope_id, imei),
  CONSTRAINT uc_imsi UNIQUE (scope_id, imsi),
  CONSTRAINT uc_iccid UNIQUE (scope_id, iccid)
) DEFAULT CHARSET=utf8;

CREATE INDEX idx_device_connection_id ON dvc_device (scope_id, connection_id);
CREATE INDEX idx_device_serial_number ON dvc_device (scope_id, serial_number);
CREATE INDEX idx_device_display_name ON dvc_device (scope_id, display_name);
CREATE INDEX idx_device_status_id ON dvc_device (scope_id, status, client_id);
CREATE INDEX idx_device_status_dn ON dvc_device (scope_id, status, display_name);
CREATE INDEX idx_device_model_id ON dvc_device (scope_id, model_id, client_id);
CREATE INDEX idx_device_model_dn ON dvc_device (scope_id, model_id, display_name);
CREATE INDEX idx_device_esf_id ON dvc_device (scope_id, app_framework_version, client_id);
CREATE INDEX idx_device_esf_dn ON dvc_device (scope_id, app_framework_version, display_name);
CREATE INDEX idx_device_app_id ON dvc_device (scope_id, app_identifiers, client_id);
CREATE INDEX idx_device_app_dn ON dvc_device (scope_id, app_identifiers, display_name);
CREATE INDEX idx_device_c1_id ON dvc_device (scope_id, custom_attribute_1, client_id);
CREATE INDEX idx_device_c1_dn ON dvc_device (scope_id, custom_attribute_1, display_name);
CREATE INDEX idx_device_c2_id ON dvc_device (scope_id, custom_attribute_2, client_id);
CREATE INDEX idx_device_c2_dn ON dvc_device (scope_id, custom_attribute_2, display_name);
CREATE INDEX idx_device_preferred_user_id ON dvc_device (scope_id, preferred_user_id);


INSERT INTO sys_configuration (
  SCOPE_ID,
  ID,
  PID,
  CONFIGURATIONS,
  CREATED_ON,
  CREATED_BY,
  MODIFIED_ON,
  MODIFIED_BY,
  OPTLOCK,
  ATTRIBUTES,
  PROPERTIES)
VALUES (1,
        3,
        'org.eclipse.kapua.service.device.registry.DeviceRegistryService',
        CONCAT('#', CURRENT_TIMESTAMP(), CHAR(13), CHAR(10),
        'maxNumberChildEntities=0', CHAR(13), CHAR(10),
        'infiniteChildEntities=true'),
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP(),
  1,
  0,
  null,
  null);

CREATE TABLE dvc_device_event (
  scope_id             	    BIGINT(21) 	  UNSIGNED NOT NULL,
  id                     	BIGINT(21) 	  UNSIGNED NOT NULL,
  created_on             	TIMESTAMP(3)  NOT NULL DEFAULT 0,
  created_by             	BIGINT(21)    UNSIGNED NOT NULL,
  
  device_id					BIGINT(21) 	  UNSIGNED NOT NULL,
  received_on				TIMESTAMP(3)  NOT NULL DEFAULT 0,
  sent_on					TIMESTAMP(3)  NULL DEFAULT NULL,
  
  pos_longitude				DECIMAL(11,8),
  pos_latitude 	            DECIMAL(11,8),
  pos_altitude              DECIMAL(11,8),
  pos_precision 			DECIMAL(11,8),
  pos_heading				DECIMAL(11,8),
  pos_speed                 DECIMAL(11,8),
  pos_timestamp             TIMESTAMP(3)  NULL DEFAULT 0,
  pos_satellites			INT,
  pos_status				INT,
  
  resource					VARCHAR(255)  NOT NULL,
  action					VARCHAR(255)  NOT NULL,
  response_code				VARCHAR(255)  NOT NULL,
  event_message				TEXT,
 
  attributes				 TEXT,
  properties                 TEXT,

  PRIMARY KEY (scope_id, id),
  
 -- FOREIGN KEY (device_id) REFERENCES dvc_device(id) ON DELETE CASCADE
  
) CHARSET=utf8;

CREATE INDEX idx_device_event_id ON dvc_device_event (scope_id, device_id, resource, action);

