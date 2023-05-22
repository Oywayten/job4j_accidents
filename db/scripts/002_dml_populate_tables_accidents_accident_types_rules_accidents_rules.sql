INSERT INTO accident_types (name)
VALUES ('Two cars'),
       ('Machine and man'),
       ('Car and bike');

INSERT INTO accidents (name, text, address, accident_type_id)
VALUES ('Crossroads accident', 'Two cars crashed at an intersection', 'Victory street, 2', 1),
       ('Pedestrian collision', 'The car was driving on the sidewalk and hit a pedestrian', 'Bucharest street, 39', 2),
       ('Motorcycle in a ditch', 'The motorcycle did not let the car pass and flew to the side of the road',
        'Country highway, 35 km', 3);

INSERT INTO rules (name)
VALUES ('Article. 1'),
       ('Article. 2'),
       ('Article. 3');

INSERT INTO accidents_rules (accident_id, rule_id)
VALUES (1, 1),
       (2, 1),
       (2, 3),
       (3, 3);
