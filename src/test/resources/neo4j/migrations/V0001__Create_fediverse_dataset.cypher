CREATE (s1:Server {
 uri:'mastodon.social', title:'Mastodon', registrations:true,
 short_description:'The original server operated by the Mastodon gGmbH non-profit'})
CREATE (meistermeier:Account {id:'106403780371229004', username:'meistermeier', display_name:'Gerrit Meier'})
CREATE (rotnroll666:Account {id:'109258442039743198', username:'rotnroll666', display_name:'Michael Simons'})
CREATE
(meistermeier)-[:REGISTERED_ON]->(s1),
(rotnroll666)-[:REGISTERED_ON]->(s1)

CREATE (s2:Server {
 uri:'chaos.social', title:'chaos.social', registrations:false,
 short_description:'chaos.social – a Fediverse instance for & by the Chaos community'})
CREATE (odrotbohm:Account {id:'108194553063501090', username:'odrotbohm', display_name:'Oliver Drotbohm'})

CREATE
(odrotbohm)-[:REGISTERED_ON]->(s2)

CREATE
(odrotbohm)-[:FOLLOWS]->(rotnroll666),
(odrotbohm)-[:FOLLOWS]->(meistermeier),
(meistermeier)-[:FOLLOWS]->(rotnroll666),
(meistermeier)-[:FOLLOWS]->(odrotbohm),
(rotnroll666)-[:FOLLOWS]->(meistermeier),
(rotnroll666)-[:FOLLOWS]->(odrotbohm)

CREATE
(s1)-[:CONNECTED_TO]->(s2)