DROP TABLE "ITEM";
  
CREATE TABLE "ITEM" 
("ID" NUMBER Generated AS IDENTITY CONSTRAINT "ITEM_PK" PRIMARY KEY, 
"NAME" VARCHAR2(50 BYTE), 
"DESCRIPTION" VARCHAR2(100 BYTE), 
"ATTACK" NUMBER DEFAULT 0, 
"ADDATTACK" NUMBER DEFAULT 0, 
"COUNT" NUMBER DEFAULT 0, 
"LIMIT" VARCHAR2(20 BYTE), 
"TIMES" NUMBER DEFAULT 1, 
"USE" NUMBER DEFAULT 0, 
"NEEDDICE" NUMBER DEFAULT 0, 
"ACTIVELIMIT" VARCHAR2(20 BYTE), 
"FIRESTACK" NUMBER DEFAULT 0, 
"ICESTACK" NUMBER DEFAULT 0, 
"ELECSTACK" NUMBER DEFAULT 0, 
"POISONSTACK" NUMBER DEFAULT 0, 
"RECOVERY" NUMBER DEFAULT 0, 
"DEFENCE" NUMBER DEFAULT 0, 
"DAMAGE" NUMBER DEFAULT 0, 
"NEWDICE" VARCHAR2(20 BYTE), 
"ACCUMULATION" NUMBER DEFAULT 0,

"ENHNAME" VARCHAR2(50 BYTE), 
"ENHDESCRIPTION" VARCHAR2(100 BYTE), 
"ENHATTACK" NUMBER DEFAULT 0, 
"ENHADDATTACK" NUMBER DEFAULT 0, 
"ENHCOUNT" NUMBER DEFAULT 0, 
"ENHLIMIT" VARCHAR2(20 BYTE), 
"ENHTIMES" NUMBER DEFAULT 1, 
"ENHUSE" NUMBER DEFAULT 0, 
"ENHNEEDDICE" NUMBER DEFAULT 0, 
"ENHACTIVELIMIT" VARCHAR2(20 BYTE), 
"ENHFIRESTACK" NUMBER DEFAULT 0, 
"ENHICESTACK" NUMBER DEFAULT 0, 
"ENHELECSTACK" NUMBER DEFAULT 0, 
"ENHPOISONSTACK" NUMBER DEFAULT 0, 
"ENHRECOVERY" NUMBER DEFAULT 0, 
"ENHDEFENCE" NUMBER DEFAULT 0, 
"ENHDAMAGE" NUMBER DEFAULT 0, 
"ENHNEWDICE" VARCHAR2(20 BYTE), 
"ENHACCUMULATION" NUMBER DEFAULT 0
);

INSERT INTO ITEM (NAME, DESCRIPTION, ATTACK) VALUES ('�׽�Ʈ��', '��������', -1);