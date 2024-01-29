-- Dùng db web_room
use web_room;

-- Tìm lớp theo owner_id
select *
from classes where owner_id = 3;


delete from classes where class_id = 2 and owner_id = 3;

select *
from class_user;


select *
from users_roles;

select *
from users;

-- exam type
insert into web_room.exam_type (exam_type)
values ('Mock test');

insert into web_room.exam_type (exam_type, submission_time)
values ('Mock test','30');

insert into web_room.exam_type (exam_type, submission_time)
values ('Mock test','45');

insert into web_room.exam_type (exam_type, submission_time)
values ('Mock test','60');

insert into web_room.exam_type (exam_type, submission_time)
values ('Mock test','90');

-- Space

insert into web_room.exam_type (exam_type, submission_time)
values ('Test','30');

insert into web_room.exam_type (exam_type, submission_time)
values ('Test','45');

insert into web_room.exam_type (exam_type, submission_time)
values ('Test','60');

insert into web_room.exam_type (exam_type, submission_time)
values ('Test','90');

select * from web_room.exam_type;

-- document_type
insert into document_types (document_type)
values ('Trắc nghiệm');
insert into document_types (document_type)
values ('Tài liệu');


select *
from exam_type;

select *
from quiz_answer;

select * from roles;

select * from documents