-- Insert Puppy Food only if not already present
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Small Breed Food', 1, 'PUPPY', 'Rich in nutrients to support small breed puppy growth.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Puppy Small Breed Food'
    AND breed_size_id = 1
    AND age_group = 'PUPPY'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Medium Breed Food', 2, 'PUPPY', 'Balanced formula for medium breed puppies.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Puppy Medium Breed Food'
    AND breed_size_id = 2
    AND age_group = 'PUPPY'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Puppy Large Breed Food', 3, 'PUPPY', 'Supports bone development for large breed puppies.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Puppy Large Breed Food'
    AND breed_size_id = 3
    AND age_group = 'PUPPY'
);

-- Insert Adult Food only if not already present
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Small Breed Food', 1, 'ADULT', 'Nourishes and maintains health for small adult dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Adult Small Breed Food'
    AND breed_size_id = 1
    AND age_group = 'ADULT'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Medium Breed Food', 2, 'ADULT', 'Promotes energy and muscle maintenance for medium breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Adult Medium Breed Food'
    AND breed_size_id = 2
    AND age_group = 'ADULT'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Adult Large Breed Food', 3, 'ADULT', 'Ensures joint support and balanced nutrition for large breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Adult Large Breed Food'
    AND breed_size_id = 3
    AND age_group = 'ADULT'
);

-- Insert Senior Food only if not already present
INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Small Breed Food', 1, 'SENIOR', 'Supports vitality and heart health for small senior dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Senior Small Breed Food'
    AND breed_size_id = 1
    AND age_group = 'SENIOR'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Medium Breed Food', 2, 'SENIOR', 'Provides joint and immune support for medium seniors.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Senior Medium Breed Food'
    AND breed_size_id = 2
    AND age_group = 'SENIOR'
);

INSERT INTO dog_food (name, breed_size_id, age_group, description)
SELECT 'Senior Large Breed Food', 3, 'SENIOR', 'Enhances mobility and well-being for large senior dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_food
    WHERE name = 'Senior Large Breed Food'
    AND breed_size_id = 3
    AND age_group = 'SENIOR'
);

-- Insert Puppy Supplements only if not already present
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Joint Support Puppy Supplement', 1, 'PUPPY', 'Enhances joint health and mobility for small breed puppies.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Joint Support Puppy Supplement'
    AND breed_size_id = 1
    AND age_group = 'PUPPY'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Digestive Health Puppy Supplement', 2, 'PUPPY', 'Supports digestive health for medium breed puppies.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Digestive Health Puppy Supplement'
    AND breed_size_id = 2
    AND age_group = 'PUPPY'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Bone Growth Puppy Supplement', 3, 'PUPPY', 'Promotes strong bone growth for large breed puppies.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Bone Growth Puppy Supplement'
    AND breed_size_id = 3
    AND age_group = 'PUPPY'
);

-- Insert Adult Supplements only if not already present
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Joint Care Adult Supplement', 1, 'ADULT', 'Maintains joint flexibility and supports small adult dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Joint Care Adult Supplement'
    AND breed_size_id = 1
    AND age_group = 'ADULT'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Skin & Coat Health Supplement', 2, 'ADULT', 'Improves skin and coat for medium adult dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Skin & Coat Health Supplement'
    AND breed_size_id = 2
    AND age_group = 'ADULT'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Large Breed Muscle Support', 3, 'ADULT', 'Enhances muscle strength and endurance for large breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Large Breed Muscle Support'
    AND breed_size_id = 3
    AND age_group = 'ADULT'
);

-- Insert Senior Supplements only if not already present
INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Senior Joint Support Supplement', 1, 'SENIOR', 'Boosts mobility and joint health for small senior dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Senior Joint Support Supplement'
    AND breed_size_id = 1
    AND age_group = 'SENIOR'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Immune Booster Senior Supplement', 2, 'SENIOR', 'Strengthens immune response in medium breed senior dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Immune Booster Senior Supplement'
    AND breed_size_id = 2
    AND age_group = 'SENIOR'
);

INSERT INTO dog_supplements (name, breed_size_id, age_group, description)
SELECT 'Cognitive Support Large Breed Supplement', 3, 'SENIOR', 'Supports cognitive health and alertness in large senior dogs.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_supplements
    WHERE name = 'Cognitive Support Large Breed Supplement'
    AND breed_size_id = 3
    AND age_group = 'SENIOR'
);

-- Insert Anti-Parasite Collars for Small Breed Size if not already present
INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'Small Guard Collar', (SELECT id FROM breed_size WHERE size_name = 'SMALL'), 'Protects small dogs from parasites.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'Small Guard Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'SMALL')
);

INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'Tiny Shield Collar', (SELECT id FROM breed_size WHERE size_name = 'SMALL'), 'Lightweight and effective anti-parasite collar for small breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'Tiny Shield Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'SMALL')
);

-- Insert Anti-Parasite Collars for Medium Breed Size if not already present
INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'Medium Defense Collar', (SELECT id FROM breed_size WHERE size_name = 'MEDIUM'), 'Durable anti-parasite collar for medium breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'Medium Defense Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'MEDIUM')
);

INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'MediSafe Collar', (SELECT id FROM breed_size WHERE size_name = 'MEDIUM'), 'Ensures medium dogs stay protected against parasites.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'MediSafe Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'MEDIUM')
);

-- Insert Anti-Parasite Collars for Large Breed Size if not already present
INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'Large Guard Collar', (SELECT id FROM breed_size WHERE size_name = 'LARGE'), 'Heavy-duty collar for large breeds against ticks and fleas.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'Large Guard Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'LARGE')
);

INSERT INTO dog_collars (collar_name, breed_size_id, description)
SELECT 'MaxShield Collar', (SELECT id FROM breed_size WHERE size_name = 'LARGE'), 'Maximum protection anti-parasite collar for large breeds.'
WHERE NOT EXISTS (
    SELECT 1 FROM dog_collars
    WHERE collar_name = 'MaxShield Collar'
    AND breed_size_id = (SELECT id FROM breed_size WHERE size_name = 'LARGE')
);




