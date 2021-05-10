create table users (
	user_id serial primary key,
	username varchar(30) unique not null,
	password varchar(30) not null,
	email varchar(100) not null,
	role char(7) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

create table students (
	user_id int unique not null,
	student_id serial primary key,
	enrolled_credits int not null check (enrolled_credits > 0 and enrolled_credits < 22),
	foreign key (user_id)
	references users (user_id)
	on delete cascade
);
	
create table teachers (
	teacher_id serial primary key,
	user_id int unique not null,
	department varchar(30),
	foreign key (user_id)
	references users(user_id)
	on delete cascade
);

create table courses (
	course_id serial primary key,
	name varchar(100) unique not null,
	description varchar(1000),
	teacher_id int,
	credit_hours int not null check(credit_hours > 0 and credit_hours < 6),
	foreign key (teacher_id)
	references teachers (teacher_id)
	on delete cascade
);

create table enrollments (
	student_id int,
	course_id int,
	primary key (student_id, course_id),
	foreign key (student_id)
	references students (student_id)
	on delete cascade,
	foreign key (course_id)
	references courses (course_id)
	on delete cascade
);

insert into users (user_id, username, password, email, role, first_name, last_name)
values (default, 'mhanlie', 'p4ssw0rd', 'missmissy@gmail.com', 'teacher', 'Missy', 'Hanlie'),
(default, 'gmyers', 'orangecat', 'graham344@gmail.com', 'teacher', 'Graham', 'Myers'),
(default, 'dirkt23', 'PasswordsAreHard', 'dirkdirkdirk@gmail.com', 'teacher', 'Dirk', 'Thomson'),
(default, 'kellyely', '42224^^', 'kelleyely@gmail.com', 'teacher', 'Kelley', 'Ely'),
(default, 'mrwarren', 'ILikeYogurt', 'thepwarren@outlook.com', 'teacher', 'Phil', 'Warren'),
(default, 'ryeeden', 'bluewhales', 'edenrye929@gmail.com', 'student', 'Eden', 'Rye'),
(default, 'williedean', 'pa44dwor', 'willdeanorwonthe@gmail.com', 'student', 'Will', 'Dean'),
(default, 'jjones', 'tacobestdog', 'jjjones6@gmail.com', 'student', 'Jerry', 'Jones'),
(default, 'thefox', 'starrynights', 'cfox0999@gmail.com', 'student', 'Carrie', 'Fox'),
(default, 'heyitsmaude', 'illrememberthisone', 'maudetownsend4@yahoo.com', 'student', 'Maude', 'Townsend'),
(default, 'jennybrock', 'awwwyeah', 'brocktherock@gmail.com', 'student', 'Jennifer', 'Brock'),
(default, 'lewin93', 'p90word', 'glewin93@gmail.com', 'student', 'George', 'Lewin'),
(default, 'markdabs', 'blehhhhbleh', 'markdabney00@gmail.com', 'student', 'Mark', 'Dabney'),
(default, 'austinsmith', 'username', 'cooladdresshere@gmail.com', 'student', 'Austin', 'Smith'),
(default, 'selsol', 'chanel55', 'selsoloman@gmail.com', 'student', 'Selena', 'Solomon');

insert into students (user_id, student_id, enrolled_credits)
values ((select user_id from users where first_name = 'Eden'), default, 3),
((select user_id from users where first_name = 'Will'), default, 3),
((select user_id from users where first_name = 'Jerry'), default, 6),
((select user_id from users where first_name = 'Carrie'), default, 6),
((select user_id from users where first_name = 'Maude'), default, 3),
((select user_id from users where first_name = 'Jennifer'), default, 3),
((select user_id from users where first_name = 'George'), default, 9),
((select user_id from users where first_name = 'Mark'), default, 3),
((select user_id from users where first_name = 'Austin'), default, 3),
((select user_id from users where first_name = 'Selena'), default, 5);

insert into teachers (user_id, teacher_id, department)
values ((select user_id from users where first_name = 'Missy'), default, 'Math'),
((select user_id from users where first_name = 'Graham'), default, 'Computer Science'),
((select user_id from users where first_name = 'Dirk'), default, 'History'),
((select user_id from users where first_name = 'Kelley'), default, 'Film Studies'),
((select user_id from users where first_name = 'Phil'), default, 'English');

insert into courses (course_id, name, description, teacher_id, credit_hours)
values (default, 'Calculus', 'An introduction to calculus', (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Missy')), 3),
(default, 'Geometry', null, (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Missy')), 3),
(default, 'Advanced Java Programming', 'An overview of advanced Java development including several in-depth Java application projects', (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Graham')), 5),
(default, 'Britain in the Industrial Revolution', 'The invention of industry and how it aided Britain in becoming a world superpower', (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Dirk')), 3),
(default, 'World History I', 'A survey of the history of human civilizations from nomadic peoples to classical antiquity', (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Dirk')), 3),
(default, 'Modern Cinema', 'An exploration of the development of the media of film and the common traits of contemporary mise-en-scene', (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Kelley')), 3),
(default, 'American Literature', null, (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Phil')), 3),
(default, 'Creative Writing Workshop', null, (select teacher_id from teachers where teacher_id = (select user_id from users where first_name = 'Phil')), 3);

create procedure enroll(name_of_user varchar, name_of_course varchar) language plpgsql as $$
begin
	insert into enrollments (student_id, course_id)
	values (
	(select student_id from students where user_id = (select user_id from users where username = name_of_user)),
	(select course_id from courses where name = name_of_course));
end;
$$

call enroll('selsol', 'Advanced Java Programming');