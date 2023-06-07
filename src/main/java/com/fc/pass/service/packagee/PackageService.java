package com.fc.pass.service.packagee;

import com.fc.pass.repository.packagee.PackageEntity;
import com.fc.pass.repository.packagee.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackage() {
        List<PackageEntity> packageEntities = packageRepository.findAllByOrderByPackageName();
        return PackageModelMapper.INSTANCE.map(packageEntities);
    }

}
