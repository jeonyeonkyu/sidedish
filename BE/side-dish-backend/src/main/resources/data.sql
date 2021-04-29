ALTER TABLE item CONVERT TO character SET utf8;
ALTER TABLE category CONVERT TO character SET utf8;

INSERT INTO category(name, best) values
                                        ('MAIN', FALSE),
                                        ('SOUP', FALSE),
                                        ('SIDE', FALSE),
                                        ('할인특가 세트상품', TRUE),
                                        ('풍성한 고기반찬', TRUE),
                                        ('바다향가득 반찬', TRUE),
                                        ('편리한 반찬세트', TRUE),
                                        ('간단한 덮밥요리', TRUE),
                                        ('우리아이 영양반찬', TRUE);
