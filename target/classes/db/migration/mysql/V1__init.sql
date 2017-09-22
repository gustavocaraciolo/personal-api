CREATE TABLE `usuario` (
  `id` INT AUTO_INCREMENT NOT NULL ,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sobre` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
   primary key (id)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `habilidades` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `percentual_conhecimento` bigint(3) DEFAULT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `sobre`
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`usuario_id`);
  
ALTER TABLE `habilidades`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`usuario_id`);

