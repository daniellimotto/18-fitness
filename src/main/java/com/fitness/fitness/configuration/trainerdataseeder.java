package com.fitness.fitness.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fitness.fitness.model.Trainer;
import com.fitness.fitness.repository.TrainerRepo;

@Component
public class trainerdataseeder implements CommandLineRunner {
    private final TrainerRepo trainerRepo;

    public trainerdataseeder(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (trainerRepo.count() == 0) {
            seedTrainer();
        }
    }

    private void seedTrainer() {
        List<Trainer> trainers = new ArrayList<>();

        // Get the absolute path to the images directory
        Path imagesDirectory = Paths.get("images").toAbsolutePath();

        try {
            // Read image files and convert them to byte arrays
            byte[] image1 = Files.readAllBytes(imagesDirectory.resolve("trainer1.jpg"));
            byte[] image2 = Files.readAllBytes(imagesDirectory.resolve("trainer2.jpg"));
            byte[] image3 = Files.readAllBytes(imagesDirectory.resolve("trainer3.jpg"));
            byte[] image4 = Files.readAllBytes(imagesDirectory.resolve("trainer4.jpg"));
            byte[] image5 = Files.readAllBytes(imagesDirectory.resolve("trainer5.jpg"));

            // Create sample fitness classes with byte arrays for images
            Trainer trainer1 = new Trainer("Chris Bumstead", 27, "Male", "1997-01-01", "cbum@gmail.com", image1, "188-888-888", 5, "2024-01-01");
            Trainer trainer2 = new Trainer("Noel Deyzel", 27, "Male", "1997-01-01", "cbum@gmail.com", image2, "188-888-888", 4, "2024-01-01");
            Trainer trainer3 = new Trainer("Sam Sulek", 27, "Male", "1997-01-01", "cbum@gmail.com", image3, "188-888-888", 3, "2024-01-01");
            Trainer trainer4 = new Trainer("Jeff Nippard", 27, "Male", "1997-01-01", "cbum@gmail.com", image4, "188-888-888", 5, "2024-01-01");
            Trainer trainer5 = new Trainer("Jill Wozby", 25, "Female", "1998-02-01", "jwoz@gmail.com", image5, "278997683", 3, "2024-02-01");

            // Add trainers to the list
            trainers.add(trainer1);
            trainers.add(trainer2);
            trainers.add(trainer3);
            trainers.add(trainer4);
            trainers.add(trainer5);

            // Save all trainers to the database
            trainerRepo.saveAll(trainers);
        } catch (IOException e) {
            // Handle IOException if image files cannot be read
            e.printStackTrace();
        }
    }
}
