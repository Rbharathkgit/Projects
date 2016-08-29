

drop table IF EXISTS community;
create table community(
community_name varchar(255) NOT NULL UNIQUE,
community_id BIGINT PRIMARY KEY 
);
insert into community(community_name,community_id) values('Loan Delivery',1001);
insert into community(community_name,community_id) values('Loan Origination',1002);
insert into community(community_name,community_id) values('Loan Coverage',1003);
insert into community(community_name,community_id) values('Asset Classification',1004);
insert into community(community_name,community_id) values('Contract Management',1005);
insert into community(community_name,community_id) values('Loan Closing',1006);
insert into community(community_name,community_id) values('Rep and Warrant Eligibility',1007);
insert into community(community_name,community_id) values('Credit Risk Assessment',1008);
commit;

drop table IF EXISTS subscriber;
create table subscriber(
subsriber_name varchar(255) NOT NULL,
subscriber_id BIGINT PRIMARY KEY,
community_id BIGINT NOT NULL,
FOREIGN KEY (community_id) REFERENCES community(community_id)
);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('Selling System',1001,1001);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('DIM',1002,1001);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('LQA',1003,1001);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('Loan Prospector',1004,1002);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('LCI',1005,1003);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('SFTAACA',1006,1004);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('Selling S2S',1007,1005);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('LCLA',1008,1006);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('GPC Selling System',1009,1007);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('GPC LQA',1010,1007);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('GPC LPA',1011,1007);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('CRA PDEP',1012,1008);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('SFMA',1013,1008);
insert into subscriber(subsriber_name,subscriber_id,community_id) values('CAR Web',1014,1008);
commit;

drop table IF EXISTS workspace;
create table workspace(
workspace_name varchar(255) NOT NULL UNIQUE,
workspace_id BIGINT PRIMARY KEY,
subscriber_id BIGINT,
community_id BIGINT,
project_name varchar(50) NOT NULL,
project_desc varchar(1000),
impl_date date,
FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id),
FOREIGN KEY (community_id) REFERENCES community(community_id)
);
insert into workspace(workspace_name,workspace_id,subscriber_id,community_id,project_name,project_desc,impl_date) 
			   values('Q3 Release',1001,1001,1001,'Q3 Release','Sample project Desc',to_date('31-JUL-2016'));
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('August Binder',1002,'Sample Project');
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Build Automation',1003,'Sample Project');			   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample1',1004,'Sample Project-123');		   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample2',1005,'Sample Project-12345');		   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample3',1006,'Sample Project-1233432');		   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample4',1007,'Sample Project-test 1');
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample5',1008,'Sample Project- test 2');		   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample6',1009,'Sample Project- test 3');		   
insert into workspace(workspace_name,workspace_id,project_name) 
			   values('Workspace-sample7',1010,'Sample Project-  test 4');
commit;

drop table IF EXISTS datapoint;
create table datapoint(
datapoint_name varchar(255) NOT NULL UNIQUE,
datapoint_id BIGINT PRIMARY KEY,
subscriber_id BIGINT NOT NULL,
community_id BIGINT NOT NULL,
xpath varchar(1000) NOT NULL,
data_type varchar(50) DEFAULT 'string',
FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id),
FOREIGN KEY (community_id) REFERENCES community(community_id)
);
insert into datapoint(datapoint_name,datapoint_id,subscriber_id,community_id,xpath) 
			   values('datapoint1',1001,1001,1001,'test/test1/test2');
insert into datapoint(datapoint_name,datapoint_id,subscriber_id,community_id,xpath) 
			   values('datapoint2',1002,1001,1001,'test/test1/test3');
commit;

drop table IF EXISTS template;
create table template(
template_name varchar(255) NOT NULL UNIQUE,
template_id BIGINT PRIMARY KEY,
subscriber_id BIGINT NOT NULL,
community_id BIGINT NOT NULL,
template_file BLOB(20M) NOT NULL,
FOREIGN KEY (subscriber_id) REFERENCES subscriber(subscriber_id),
FOREIGN KEY (community_id) REFERENCES community(community_id)
);
insert into template(template_name,template_id,subscriber_id,community_id,template_file) 
			   values('template1',1001,1001,1001,'test/test1/test2');
insert into template(template_name,template_id,subscriber_id,community_id,template_file)
			   values('template2',1002,1001,1001,'test/test1/test2');
commit;