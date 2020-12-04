package com.ozgs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	

	public List<String> searchProfileText(String user) throws SQLException, ClassNotFoundException {

		List<String> res=jdbcTemplate.query("select text from profiles where user=?",

		new Object[]{user},
		new RowMapper<String>() {

			@Override
					public String mapRow(ResultSet arg0, int arg1) throws SQLException {

						String text=arg0.getString("text");

						return text;
					}
		});

				return res;
	}


	public List<User> searchName(String user) throws SQLException, ClassNotFoundException {

		List<User> res=jdbcTemplate.query("select user,pass from members where user=?",

		new Object[]{user},
		new RowMapper<User>() {

			@Override
					public User mapRow(ResultSet arg0, int arg1) throws SQLException {

						User user=new User();

						user.setName(arg0.getString("user"));

						return user;
					}
		});

				return res;
	}

	public List<User> searchUser(User user) throws SQLException, ClassNotFoundException {

		List<User> res=jdbcTemplate.query("select user,pass from members where user=?",

		new Object[]{ user.getName() },

		new RowMapper<User>() {

			@Override
					public User mapRow(ResultSet arg0, int arg1) throws SQLException {

						User user=new User();

						user.setName(arg0.getString("user"));

						return user;
					}
		});

				return res;
	}

		public List<User> search(User user) throws SQLException, ClassNotFoundException {

		List<User> res=jdbcTemplate.query("select user,pass from members where user=? and pass=?",
		new Object[]{ user.getName(), user.getPassword() },
		new RowMapper<User>() {

			@Override
					public User mapRow(ResultSet arg0, int arg1) throws SQLException {

						User user=new User();

						user.setName(arg0.getString("user"));

						user.setPassword(arg0.getString("pass"));

						return user;
					}
		});

				return res;
	}

	public List<Message> searchMessages(String recip) throws SQLException, ClassNotFoundException {

		List<Message> res=jdbcTemplate.query("select * from messages where recip=? order by 'time' desc",
		
		new Object[]{recip},

		new RowMapper<Message>() {

			@Override
					public Message mapRow(ResultSet arg0, int arg1) throws SQLException {

						Message message=new Message();

						int ID=arg0.getInt("id");
						
						message.setID(ID);

						String author=arg0.getString("auth");

						message.setAuthor(author);
 
						String recipient=arg0.getString("recip");

						message.setRecipient(recipient);

						Boolean pm=arg0.getBoolean("pm");

						message.setPm(pm);

						Timestamp timestamp=arg0.getTimestamp("time");

						message.setTimestamp(timestamp);

						String text=arg0.getString("message");

						message.setText(text);

						return message;
					}
		});

				return res;
	}
	public List<String> searchFriends(String user, String friend) throws SQLException, ClassNotFoundException {

		List<String> res=jdbcTemplate.query("select * from friends where user=? and friend=?",

		new Object[]{user,friend},

		new RowMapper<String>() {
			

			@Override
					public String mapRow(ResultSet arg0, int arg1) throws SQLException {

						String userName=arg0.getString("user");

						return userName;
					}
		});

				return res;
	}

	public List<String> searchMembers() throws SQLException, ClassNotFoundException {

		List<String> res=jdbcTemplate.query("select * from members",

		new RowMapper<String>() {
			

			@Override
					public String mapRow(ResultSet arg0, int arg1) throws SQLException {


						String userName=arg0.getString("user");

						return userName;
					}
		});

				return res;
	}
	
	public int removeFriend(String user, String friend) throws SQLException,ClassNotFoundException
	{
		
		return jdbcTemplate.update("delete from friends where user=? and friend=?",user,friend);
		
	}
	public int insertFriend(String user, String friend) throws SQLException,ClassNotFoundException
	{
		
		return jdbcTemplate.update("insert into friends values(?,?)",user,friend);
		
	}
	public int insertMember(User user) throws SQLException,ClassNotFoundException
	{
		return jdbcTemplate.update("insert into members values(?,?)",user.getName(),user.getPassword());
		
	}

	public int insertProfileText(String userName, String userText) throws SQLException, ClassNotFoundException
	{
		return jdbcTemplate.update("insert into profiles(user,text) values(?,?)", userName, userText);

	}

	public int updateProfileText(String userName, String userText) throws SQLException, ClassNotFoundException {

		return jdbcTemplate.update("update profiles set text=? where user=?", userText, userName);
		
	}

	public int insertMessageText(String user, String view, Timestamp timeStamp, int privateMessage, String text) throws SQLException, ClassNotFoundException {

		return jdbcTemplate.update("insert into messages (auth,recip,pm,time,message) values (?,?,?,?,?)", user,view,privateMessage,timeStamp,text);

	}


	public int deleteMessages(String eraseID) throws SQLException, ClassNotFoundException {

		return jdbcTemplate.update("delete from messages where id=?", Integer.parseInt(eraseID));


	}

}
