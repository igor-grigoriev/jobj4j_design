select avg(price) from devices;

select dp.people_id, avg(d.price) 
from devices_people as dp
join devices as d 
on dp.device_id  = d.id
group by dp.people_id;

select dp.people_id, avg(d.price) 
from devices_people as dp
join devices as d 
on dp.device_id  = d.id
group by dp.people_id
having avg(d.price) > 5000;