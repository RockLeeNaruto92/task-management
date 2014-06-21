package hust.arrowtech.taskmanagement.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class EmCreator implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3324696613518787760L;

	@PersistenceContext(name = "TestArquillian")
    private EntityManager em;

    public EntityManager getEm()
    {
        return this.em;
        // if (this.manualCreatedEm == null)
        // {
        // this.manualCreatedEm = Persistence.createEntityManagerFactory("TestArquillian").createEntityManager();
        // }
        // return this.manualCreatedEm;
    }

}
