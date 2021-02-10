package com.ozgs;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class SocController {

    private static final String UPLOAD_DIRECTORY = "/usr/local/images";
    // private static final String UPLOAD_DIRECTORY = "/images";
    // private static final String UPLOAD_DIRECTORY = "/resources/images";

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/")
    public String indexPage(Model m) {

        return "index";
    }

    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    public String checkUser(Model m, HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");

        String user = request.getParameter("user");

        List<User> list = null;

        try {
            list = userDao.searchName(user);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("thisList", list.size());

        return "checkuser";
    }

    @RequestMapping(value = "/logout")
    public String logoutPage(Model m) {

        return "logout";
    }

    @RequestMapping(value = "/login")
    public String loginForm(Model m) {

        m.addAttribute("something", new User());

        m.addAttribute("userString", "Guest");

        return "loginform";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginResult(@ModelAttribute("something") User user, Model m, HttpSession httpSession) {

        List<User> result = null;

        try {

            result = userDao.search(user);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (result.size() != 0) {

            httpSession.setAttribute("sessionParam", user.getName());

            m.addAttribute("userString", user.getName());

            return "clickform";
        }

        else

        {

            m.addAttribute("message", "Username/Password invalid");

            m.addAttribute("userString", "Guest");

            return "loginform";
        }
    }

    @RequestMapping(value = "/signup")
    public String signupForm(Model m) {

        m.addAttribute("signup", new User());

        return "signupform";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupResult(Model m, @Valid @ModelAttribute("signup") User user, BindingResult bindingResult) {

        String errorMessage = null;

        m.addAttribute("userName", user.getName());

        if (bindingResult.hasErrors()) {
            return "signupform";
        }
        
        else {

            List<User> result = null;

            try {

                result = userDao.searchUser(user);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (result.size() != 0) {

                errorMessage = "this user name already exists";
                m.addAttribute("error", errorMessage);
            }

            else {
                try {

                    int res = userDao.insertMember(user);

                    if (res != 0)
                        m.addAttribute("account", "Account created, please log in");

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return "signupform";
    }

    @RequestMapping(value = "/profile")
    public String profileUpload(Model m, HttpSession session) {

        String userName = (String) session.getAttribute("sessionParam");

        String filePath = Paths.get(UPLOAD_DIRECTORY).toString() + File.separator + userName + ".jpg";

        // m.addAttribute("src", filePath);
        m.addAttribute("src", "/images" + File.separator + userName + ".jpg");

        List<String> list = null;

        String userText = null;

        try {
            list = userDao.searchProfileText(userName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (list.size() != 0)
            userText = list.get(0);

        else
            userText = "";

        m.addAttribute("text", userText);

        return "profile";
    }

    @RequestMapping(value = "/messages", method = { RequestMethod.GET, RequestMethod.POST })
    public String messages(Model m, HttpSession session, HttpServletRequest request) {

        String user = (String) session.getAttribute("sessionParam");

        if (user == null)
            return "message";

        String view = request.getParameter("view");

        m.addAttribute("view", view);

        String nameString = null;

        if (view == null) {

            view = user;

        }

        if (view.equals(user)) {

            nameString = "Your";

        }

        else
            nameString = "<a href='members?view=" + view + "'>" + view + "'s</a>";

        m.addAttribute("nameString", nameString);

        m.addAttribute("view", view);

        String text = request.getParameter("text");

        if (text != null) {

            Timestamp timeStamp = new Timestamp(new Date().getTime());

            int privateMessage = Integer.parseInt(request.getParameter("pm"));

            try {

                userDao.insertMessageText(user, view, timeStamp, privateMessage, text);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }

        String eraseID = request.getParameter("erase");

        if (eraseID != null) {

            try {
                userDao.deleteMessages(eraseID);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        List<Message> messageRow=null;

        try {
                messageRow=userDao.searchMessages(view);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("messageRow", messageRow);

        return "message";

    }

    @RequestMapping(value = "/friends")
    public String friends(Model m, HttpSession session)
    {
        String userName = (String) session.getAttribute("sessionParam");

        List<String> memberList=new ArrayList<>();
        List<String> userList=new ArrayList<>();
        List<String> friendList=new ArrayList<>();
        List<String> mutualList=new ArrayList<>();
        List<String> followersList=new ArrayList<>();
        List<String> followingList=new ArrayList<>();


        try {
            memberList = userDao.searchMembers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        for(String member:memberList){

            if (member.equals(userName)) continue;

            try {
                userList = userDao.searchFriends(member,userName);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

             try {
                friendList = userDao.searchFriends(userName,member);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (userList.size()+friendList.size()>1) {
                
                mutualList.add(member);

            }

            else if (userList.size()==1) {
                
                followingList.add(member);

            }

            else if (friendList.size()==1){

                followersList.add(member);
            } 

        }

        m.addAttribute("mutualList", mutualList);
        
        m.addAttribute("followingList", followingList);

        m.addAttribute("followersList", followersList);

        return "friends";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profile(Model m, HttpServletRequest request, HttpSession session,
            @RequestParam CommonsMultipartFile file) {

        String userName = (String) session.getAttribute("sessionParam");

        if (file != null) {

            String fileName = null;

            Boolean typeOK = true;

            String fileType = file.getContentType();

            switch (fileType) {
                case "image/jpeg":
                    fileName = userName + ".jpeg";
                    break;
                case "image/jpg":
                    fileName = userName + ".jpg";
                    break;
                case "image/png":
                    fileName = userName + ".png";
                    break;
                case "image/gif":
                    fileName = userName + ".gif";
                    break;
                case "image/bmp":
                    fileName = userName + ".bmp";
                    break;
                default:
                    typeOK = false;
            }

            if (typeOK) {

                String filePath = Paths.get(UPLOAD_DIRECTORY).toString() + File.separator + fileName;

                byte[] bytes = file.getBytes();

                try {

                    BufferedOutputStream bOutputStream = new BufferedOutputStream(
                            new FileOutputStream(new File(filePath)));
                 
                    bOutputStream.write(bytes);

                    bOutputStream.flush();

                    bOutputStream.close();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    File tempFile = new File(filePath);

                    BufferedImage bufferedImage = ImageIO.read(tempFile);

                    BufferedImage newBufferedImage = new BufferedImage(84, 84, BufferedImage.TYPE_INT_RGB);

                    newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, 84, 84, null);

                    ImageIO.write(newBufferedImage, "jpg",
                            new File(Paths.get(UPLOAD_DIRECTORY).toString() + File.separator + userName + ".jpg"));

                    tempFile.delete();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            m.addAttribute("src", "/images" + File.separator + userName + ".jpg");
            // m.addAttribute("src", Paths.get(UPLOAD_DIRECTORY).toString() + File.separator + userName + ".jpg");

        }

        List<String> list = null;

        try {
            list = userDao.searchProfileText(userName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String userText = (String) request.getParameter("text");

        if (!userText.isEmpty()) {

            if (list.size() == 0) {

                try {
                    userDao.insertProfileText(userName, userText);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }

            else {

                try {
                    userDao.updateProfileText(userName, userText);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        else {

            if (list.size() != 0)
                userText = list.get(0);

            else
                userText = "";

        }

        m.addAttribute("text", userText);

        return "profile";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String members(HttpServletRequest request, Model m, HttpSession session) {

        String userName = (String) session.getAttribute("sessionParam");

        String addUser = request.getParameter("add");

        String dropUser = request.getParameter("remove");

        List<String> memberList = null, userList = null, friendList = null;

        List<List<String>> modelList = new ArrayList<>();

         if (dropUser != null) {

            try {
                userList = userDao.searchFriends(dropUser, userName);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (userList.size() != 0)
                try {
                    userDao.removeFriend(dropUser,userName);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

        }

       if (addUser != null) {

            try {
                userList = userDao.searchFriends(addUser, userName);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (userList.size() == 0)
                try {
                    userDao.insertFriend(addUser,userName);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

        }

        String view = request.getParameter("view");

        if (view != null) {

            if (view.equals(userName))

                m.addAttribute("userName", "Your Profile");

            else

                m.addAttribute("userName", view + " 's"+" Profile");

            List<String> list = null;

            try {
                list = userDao.searchProfileText(view);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            m.addAttribute("src","/images" + File.separator + view + ".jpg");
            // m.addAttribute("src", Paths.get(UPLOAD_DIRECTORY).toString() + File.separator + view + ".jpg");

            String userText = null;

            if (list.size() != 0)
                userText = list.get(0);

            else
                userText = "";

            m.addAttribute("text", userText);

            m.addAttribute("linkMessage","<a class='button' href='messages?view="+view+"'>view messages</a>");

            return "members";

        }

        m.addAttribute("membersText","Other Members");

        try {
            memberList = userDao.searchMembers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        for(String member:memberList){

            List<String> dummyList=new ArrayList<>();

            if (member.equals(userName)) continue;

            dummyList.add(member);

            try {
                userList = userDao.searchFriends(member,userName);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

             try {
                friendList = userDao.searchFriends(userName,member);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (userList.size()+friendList.size()>1) {
                
                dummyList.add("&harr; is a mutual friend");

                dummyList.add("<a href='members?remove="+member+"'>[drop]</a>");

            }

            else if (userList.size()==1) {
                
                dummyList.add("&larr; you are following");

                dummyList.add("<a href='members?remove="+member+"'>[drop]</a>");

            }

            else if (friendList.size()==1){

                dummyList.add("&rarr; is following you");

                dummyList.add("<a href='members?add="+member+"'>[recip]</a>");
            } 

            else {
                
                dummyList.add("");

                dummyList.add("<a href='members?add="+member+"'>[follow]</a>");
            }

            modelList.add(dummyList);

        }
      
         m.addAttribute("modelList",modelList);
        
         return "members";
    }
}
