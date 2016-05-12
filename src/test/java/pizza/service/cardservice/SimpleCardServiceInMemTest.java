package pizza.service.cardservice;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pizza.domain.AccumulativeCard;
import pizza.service.CardService;
import pizza.service.InMemTest;

import com.mysql.jdbc.Statement;

public class SimpleCardServiceInMemTest extends InMemTest {
	
	@Autowired
	private CardService cardService;
	
	@Test
	public void testGetAllCards() {
		insertCardAndGetId();
		insertCardAndGetId();
		insertCardAndGetId();
		int expected = 3;
		int result = cardService.getAllCards().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testInsertCard() {
		AccumulativeCard card1 = new AccumulativeCard();
		AccumulativeCard card2 = new AccumulativeCard();
		AccumulativeCard card3 = new AccumulativeCard();
		cardService.insertCard(card1);
		cardService.insertCard(card2);
		cardService.insertCard(card3);
		int expected = 3;
		int result = getAllCardsFromDatabase().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCard() {
		AccumulativeCard card = new AccumulativeCard();
		card.setMoney(100500);
		cardService.insertCard(card);
		AccumulativeCard expected = card;
		AccumulativeCard result = cardService.getCard(card.getId());
		assertEquals(expected.getMoney(), result.getMoney(), 0.0001);
	}
	
	@Test
	public void testUpdate() {
		AccumulativeCard card = new AccumulativeCard();
		card.setMoney(100500);
		cardService.insertCard(card);
		double newMoney = 500100;
		card.setMoney(newMoney);
		cardService.updateCard(card);
		double expected = newMoney;
		double result = cardService.getCard(card.getId()).getMoney();
		assertEquals(expected, result, 0.0001);
	}
	
	@Test
	public void testDelete() {
		int cardId = insertCardAndGetId();
		insertCardAndGetId();
		insertCardAndGetId();
		int expected = 3;
		int result = getAllCardsFromDatabase().size();
		assertEquals(expected, result);
		
		cardService.deleteCard(cardId);
		expected = 2;
		result = getAllCardsFromDatabase().size();
		assertEquals(expected, result);
	}
	
	private int insertCardAndGetId() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("INSERT INTO accumulative_card (money) VALUES (100)", Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
	}
	
	private List<AccumulativeCard> getAllCardsFromDatabase() {
		String sql = "SELECT money FROM accumulative_card";
		List<AccumulativeCard> cards = this.jdbcTemplate.query(sql, new RowMapper<AccumulativeCard>() {

			@Override
			public AccumulativeCard mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				AccumulativeCard card = new AccumulativeCard();
				card.setMoney(rs.getDouble("money"));
				return card;
			}

		});
		return cards;
	}

}
