package by.yavid.Service.Bazis;

import by.yavid.Entity.Workbase.Bazis.KDList;
import by.yavid.Repository.Workbase.Bazis.KDListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.persistence.Convert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KDListServiceImpl implements KDListService {

    private Environment env;
    private KDListRepository kdListRepository;

    public KDListServiceImpl(Environment env, KDListRepository kdListRepository) {
        this.env = env;
        this.kdListRepository = kdListRepository;
    }

    @Transactional(transactionManager="workbaseTransactionManager")
    @Override
    public KDList saveKDList(KDList kdList) {
        return kdListRepository.save(kdList);
    }

    @Override
    public Integer getNewKD() {
        try {
            File file = new File(env.getProperty("file.SetComboBox"));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            Node node = doc.getElementsByTagName("NomerKD")
                    .item(0).getChildNodes()
                    .item(0).getChildNodes()
                    .item(0).getChildNodes()
                    .item(0);
            Integer kd = Integer.parseInt(node.getNodeValue()) + 1;
            node.setNodeValue(kd.toString());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);

            transformer.transform(source, result);

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "windows-1251");
            BufferedWriter buffWriter = new BufferedWriter(osw);
            buffWriter.write(sw.toString());
            buffWriter.flush();
            buffWriter.close();

            return kd;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Transactional(transactionManager="workbaseTransactionManager")
    @Override
    public KDList getKdList(String numberKD) {
        Optional<KDList> kdList = kdListRepository.findByNumberKD(numberKD);
        return kdList.orElse(new KDList());
    }

    @Override
    public KDList getKdList(Integer id) {
        Optional<KDList> kdList = kdListRepository.findById(id);
        return kdList.orElse(new KDList());
    }



    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public List<KDList> getAllKdList() {
        return  StreamSupport.stream(kdListRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public KDList getKdListByUser(int idUser) {
        Optional<KDList> kdList = kdListRepository.findByUser_Id(idUser);
        return kdList.orElse(new KDList());
    }
}
