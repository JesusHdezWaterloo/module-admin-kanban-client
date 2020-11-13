/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.admin.kanban.consume.usecase_impl;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.clean.core.app.usecase.*;
import com.jhw.module.admin.kanban.consume.module.KanbanConsumeCoreModule;
import com.jhw.module.admin.kanban.consume.repo_impl.*;
import com.jhw.module.admin.kanban.consume.usecase_def.*;
import com.jhw.module.admin.kanban.core.domain.*;
import com.jhw.swing.util.ClipboardUtils;
import com.jhw.utils.file.Browser;
import com.jhw.utils.file.Opener;
import java.util.List;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ProyectoUseCaseImpl extends DefaultCRUDUseCase<ProyectoDomain> implements ProyectoUseCaseConsume {

    private final ProyectoRepoImpl repoUC = KanbanConsumeCoreModule.getInstance().getImplementation(ProyectoRepoImpl.class);

    public ProyectoUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public boolean hasRemote(ProyectoDomain proyecto) {
        try {
            //creo el repo, con el .git detras
            Repository localRepo = new FileRepository(proyecto.getUrlLocal() + "\\.git");

            //git para hacer comandos
            Git git = new Git(localRepo);
            return !git.remoteList().call().isEmpty();
        } catch (Exception e) {
        }

        return false;
    }

    @Override
    public void irACarpeta(ProyectoDomain proyecto) {
        try {
            Opener.from(proyecto.getUrlLocal()).open();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public void irARepoOnline(ProyectoDomain proyecto) {
        try {
            Browser.from(proyecto.getUrlRepoOnline()).browse();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public void copiarURLLocal(ProyectoDomain proyecto) {
        try {
            ClipboardUtils.copy(proyecto.getUrlLocal());
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    /**
     * No funciona, no se ha echo la integracion con git
     *
     * @param proyecto
     * @throws Exception
     * @deprecated
     */
    @Override
    @Deprecated
    public void updateRemote(ProyectoDomain proyecto) throws Exception {
        //creo el repo, con el .git detras
        Repository localRepo = new FileRepository(proyecto.getUrlLocal() + "\\.git");

        //rama actual
        String actualBranch = localRepo.getFullBranch();
        Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SIMPLE_TEXT,
                "Rama actual: " + actualBranch);

        //git para hacer comandos
        Git git = new Git(localRepo);

        //Chequeo si hay ficheros por hacer un commit
        List<DiffEntry> changes = git.diff().call();
        if (!changes.isEmpty()) {
            throw new Exception("No se puede hacer un update ya que hay cambios uncomited. " + changes);
        }

        //lista de las ramas
        List<Ref> branches = git.branchList().call();
        for (Ref b : branches) {

            Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SIMPLE_TEXT,
                    "Cambiando a rama: " + b.getName());
            git.checkout().setName(b.getName()).call();

            Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SIMPLE_TEXT,
                    "Pull para actualizar local");
            System.out.println();
            git.pull().call();

            Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SIMPLE_TEXT,
                    "Push para actualizar remote");
            git.push().setCredentialsProvider(
                    new UsernamePasswordCredentialsProvider(
                            "JesusHdezWaterloo",
                            "A123b456**"
                    )
            ).call();
        }
        /*
        //lista de las tags
        List<Ref> tags = git.tagList().call();
        for (Ref t : tags) {

            System.out.println("Cambiando a tag: " + t.getName());
            git.checkout().setName(t.getName()).call();

            System.out.println("Push para actualizar remote");
            git.push().setCredentialsProvider(
                    new UsernamePasswordCredentialsProvider(
                            "JesusHdezWaterloo",
                            "A123b456**"
                    )
            ).call();
        }*/
        //regreso a la rama original
        Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SIMPLE_TEXT,
                "Checkout para la rama original: " + actualBranch);
        git.checkout().setName(actualBranch).call();
        Notification.showNotification(NotificationsGeneralType.NOTIFICATION_SUCCESS,
                "Actualizadas todas las ramas con el/los repos online");
    }
}
