-- Insert Food only if dog_food table is empty
-- Puppy Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Small Breed Food', 1, 'PUPPY', 'Rich in nutrients to support small breed puppy growth.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Medium Breed Food', 2, 'PUPPY', 'Balanced formula for medium breed puppies.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Large Breed Food', 3, 'PUPPY', 'Supports bone development for large breed puppies.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

-- Adult Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Small Breed Food', 1, 'ADULT', 'Nourishes and maintains health for small adult dogs.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Medium Breed Food', 2, 'ADULT', 'Promotes energy and muscle maintenance for medium breeds.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Large Breed Food', 3, 'ADULT', 'Ensures joint support and balanced nutrition for large breeds.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

-- Senior Food
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Small Breed Food', 1, 'SENIOR', 'Supports vitality and heart health for small senior dogs.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Medium Breed Food', 2, 'SENIOR', 'Provides joint and immune support for medium seniors.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Large Breed Food', 3, 'SENIOR', 'Enhances mobility and well-being for large senior dogs.'
WHERE (SELECT COUNT(*) FROM dog_food) < 1;

-- Insert Supplements only if dog_supplements table is empty
-- Puppy Supplements
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Joint Support Puppy Supplement', 1, 'PUPPY', 'Enhances joint health and mobility for small breed puppies.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Digestive Health Puppy Supplement', 2, 'PUPPY', 'Supports digestive health for medium breed puppies.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Bone Growth Puppy Supplement', 3, 'PUPPY', 'Promotes strong bone growth for large breed puppies.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

-- Adult Supplements
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Joint Care Adult Supplement', 1, 'ADULT', 'Maintains joint flexibility and supports small adult dogs.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Skin & Coat Health Supplement', 2, 'ADULT', 'Improves skin and coat for medium adult dogs.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Large Breed Muscle Support', 3, 'ADULT', 'Enhances muscle strength and endurance for large breeds.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

-- Senior Supplements
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Senior Joint Support Supplement', 1, 'SENIOR', 'Boosts mobility and joint health for small senior dogs.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Immune Booster Senior Supplement', 2, 'SENIOR', 'Strengthens immune response in medium breed senior dogs.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Cognitive Support Large Breed Supplement', 3, 'SENIOR', 'Supports cognitive health and alertness in large senior dogs.'
WHERE (SELECT COUNT(*) FROM dog_supplements) < 1;
