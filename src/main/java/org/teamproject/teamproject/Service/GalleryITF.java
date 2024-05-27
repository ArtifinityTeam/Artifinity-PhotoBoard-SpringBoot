package org.teamproject.teamproject.Service;


import org.teamproject.teamproject.Vo.GalleryVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GalleryITF {
    List<Map<String,Object>> GalleryList(int userid);

    int addBoard(GalleryVo galleryVo);

}

