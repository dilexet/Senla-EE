explain analyse
select *
from users u
         left outer join users_chats uc
                         on u.Id = uc.user_id
         left outer join chats c
                         on c.id = uc.chat_id