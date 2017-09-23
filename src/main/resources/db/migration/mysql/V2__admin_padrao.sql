INSERT INTO `usuario` (`id`, `email`, `nome`, `senha`, `perfil`, `data_atualizacao`, `data_criacao`) 
VALUES (NULL, 'admin@admin.com', 'Administrador', 
'$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m',
'ROLE_ADMIN',
CURRENT_DATE(), CURRENT_DATE());

(SELECT `id` FROM `usuario` WHERE `email` = 'admin@admin.com');