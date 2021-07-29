INSERT INTO author(id, name) VALUES(1, 'a'), (2, 'b');
INSERT INTO novel(id, author_id, name) VALUES(1, 1, 'a1'), (2, 1, 'a2'), (3, 1, 'a3'),
                                              (4, 2, 'b1'), (5, 2, 'b2'), (6, 2, 'b3');