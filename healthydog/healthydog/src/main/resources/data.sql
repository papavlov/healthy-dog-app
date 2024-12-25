-- Puppy Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Small Breed Food', 1, 'PUPPY', 'Rich in nutrients to support small breed puppy growth.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Puppy Small Breed Food' AND breed_size_id = 1 AND age_group = 'Puppy');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Medium Breed Food', 2, 'PUPPY', 'Balanced formula for medium breed puppies.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Puppy Medium Breed Food' AND breed_size_id = 2 AND age_group = 'Puppy');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Large Breed Food', 3, 'PUPPY', 'Supports bone development for large breed puppies.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Puppy Large Breed Food' AND breed_size_id = 3 AND age_group = 'Puppy');

-- Adult Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Small Breed Food', 1, 'ADULT', 'Nourishes and maintains health for small adult dogs.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Adult Small Breed Food' AND breed_size_id = 1 AND age_group = 'Adult');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Medium Breed Food', 2, 'ADULT', 'Promotes energy and muscle maintenance for medium breeds.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Adult Medium Breed Food' AND breed_size_id = 2 AND age_group = 'Adult');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Large Breed Food', 3, 'ADULT', 'Ensures joint support and balanced nutrition for large breeds.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Adult Large Breed Food' AND breed_size_id = 3 AND age_group = 'Adult');

-- Senior Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Small Breed Food', 1, 'SENIOR', 'Supports vitality and heart health for small senior dogs.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Senior Small Breed Food' AND breed_size_id = 1 AND age_group = 'Senior');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Medium Breed Food', 2, 'SENIOR', 'Provides joint and immune support for medium seniors.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Senior Medium Breed Food' AND breed_size_id = 2 AND age_group = 'Senior');

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Large Breed Food', 3, 'SENIOR', 'Enhances mobility and well-being for large senior dogs.'
WHERE NOT EXISTS (SELECT 1 FROM dog_food WHERE name = 'Senior Large Breed Food' AND breed_size_id = 3 AND age_group = 'Senior');
