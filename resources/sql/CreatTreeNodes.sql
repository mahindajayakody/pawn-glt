DELIMITER $$

DROP PROCEDURE IF EXISTS `pawn_pmb`.`CreatTreeNodes` $$
CREATE PROCEDURE `pawn_pmb`.`CreatTreeNodes`()
begin
    declare progid,tmpParentId int;
    declare nodeName,url ,access varchar;
    
    set progid = 0, nodeName='',url='',access='',tmpParentId=30;  
    
    select progid = max(prgid) from pawn_pmb.tblsystemprogram where parentid = tmpParentId group by parentId
    

END $$

DELIMITER ;
call pawn_pmb.CreatTreeNodes();