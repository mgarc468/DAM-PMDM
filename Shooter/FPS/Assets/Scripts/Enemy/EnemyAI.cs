using UnityEngine;

public class EnemyAI : MonoBehaviour
{
    public Transform player;
    public float speed = 3.5f;
    public float attackRange = 2f;
    
    private UnityEngine.AI.NavMeshAgent agent;

    void Start()
    {
        agent = GetComponent<UnityEngine.AI.NavMeshAgent>();
        agent.speed = speed;
    }

    void Update()
    {
        if (player != null)
        {
            agent.SetDestination(player.position);

            float distance = Vector3.Distance(transform.position, player.position);
            if (distance <= attackRange)
            {
                AttackPlayer();
            }
        }
    }

    void AttackPlayer()
    {
        Debug.Log("Atacando al jugador...");
        // Aquí puedes implementar un sistema de daño al jugador
    }
}
