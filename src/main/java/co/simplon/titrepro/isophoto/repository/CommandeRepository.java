package co.simplon.titrepro.isophoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.titrepro.isophoto.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, String> {

}
