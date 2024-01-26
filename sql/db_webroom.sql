create table if not exists document_types
(
    document_type_id int auto_increment
        primary key,
    document_type    varchar(20) null
);

create table if not exists exam_type
(
    exam_type_id    int auto_increment
        primary key,
    exam_type       varchar(20) null,
    submission_time int         null
);

create table if not exists users
(
    id             bigint auto_increment
        primary key,
    email          varchar(255) not null,
    email_verified bit          not null,
    image_url      varchar(255) null,
    name           varchar(255) not null,
    password       varchar(255) null,
    provider       varchar(255) not null,
    provider_id    varchar(255) null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

create table if not exists classes
(
    class_id     bigint auto_increment
        primary key,
    owner_id     bigint      null,
    class_name   varchar(30) null,
    class_code   varchar(10) null,
    created_date datetime    null,
    constraint classes_users_id_fk
        foreign key (owner_id) references users (id)
);

create table if not exists class_user
(
    class_id    bigint     not null,
    user_id     bigint     not null,
    is_accepted tinyint(1) null,
    primary key (class_id, user_id),
    constraint class_user_classes_class_id_fk
        foreign key (class_id) references classes (class_id),
    constraint class_user_users_id_fk
        foreign key (user_id) references users (id)
);

create table if not exists documents
(
    document_id      bigint auto_increment
        primary key,
    document_title   varchar(50)  null,
    document_type_id int          null,
    document_url     varchar(100) null,
    owner_id         bigint       null,
    created_date     datetime     null,
    constraint documents_document_types_document_type_id_fk
        foreign key (document_type_id) references document_types (document_type_id),
    constraint documents_users_fk
        foreign key (owner_id) references users (id)
);

create table if not exists classes_documents
(
    document_id bigint     not null,
    class_id    bigint     not null,
    is_active   tinyint(1) null,
    primary key (class_id, document_id),
    constraint classes_documents_classes_class_id_fk
        foreign key (class_id) references classes (class_id),
    constraint classes_documents_documents_document_id_fk
        foreign key (document_id) references documents (document_id)
);

create table if not exists exams
(
    exam_id      bigint auto_increment
        primary key,
    class_id     bigint      null,
    user_id      bigint      null,
    started_date datetime    null,
    ended_date   datetime    null,
    exam_type_id int         null,
    status       varchar(10) null,
    constraint exams_classes_class_id_fk
        foreign key (class_id) references classes (class_id),
    constraint exams_exam_type_exam_type_id_fk
        foreign key (exam_type_id) references exam_type (exam_type_id),
    constraint exams_users_id_fk
        foreign key (user_id) references users (id)
);

create table if not exists quiz
(
    quiz_id     bigint auto_increment
        primary key,
    document_id bigint   null,
    question    longtext null,
    constraint d
        foreign key (document_id) references documents (document_id)
);

create table if not exists quiz_answer
(
    answer_id    bigint     not null
        primary key,
    quiz_id      bigint     null,
    answer       longtext   null,
    is_corrected tinyint(1) null,
    constraint quiz_answer_quiz_quiz_id_fk
        foreign key (quiz_id) references quiz (quiz_id)
);

create table if not exists exam_quiz
(
    exam_quiz_id bigint auto_increment
        primary key,
    exam_id      bigint null,
    answer_id    bigint null,
    answer       int    null,
    constraint exam_quiz_exams_exam_id_fk
        foreign key (exam_id) references exams (exam_id),
    constraint exam_quiz_quiz_answer_answer_id_fk
        foreign key (answer_id) references quiz_answer (answer_id)
);

